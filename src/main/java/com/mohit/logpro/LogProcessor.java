package com.mohit.logpro;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mohit.doa.LogProfileDaoImpl;
import com.mohit.model.LogProfile;

@Component
public class LogProcessor {

	@Autowired
	static LogProfileDaoImpl logprdao;
	private static final Log log = LogFactory.getLog(LogProcessor.class);
	
	public static void main(String args[]) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		logprdao = (LogProfileDaoImpl) context.getBean("logproDao");

		// String fileName = "c://AppLogs.log";

		String fileName = args[0];
		if (fileName != null) {
			processLogFile(fileName);
		} else {
			log.error("Please enter file name ");
		}

	}

	public static void processLogFile(String fileName) {
		
		log.info(" Log Processing is starting");

		Map<String, List<LogProfile>> list = new HashMap<>();
		Gson gson = new Gson();

		List<LogProfile> logproList = new ArrayList<>();

		/**
		 * Reading the log file and generating java model and generating map with start and end log
		 */
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			list = stream.map(str -> {

				LogProfile logpro = gson.fromJson(str, LogProfile.class);

				return gson.fromJson(str, LogProfile.class);
			}).sorted().collect(Collectors.groupingBy(LogProfile::getiD));

			logproList = list.entrySet().stream().map(entery -> {
				List listofobj = entery.getValue();

				LogProfile start = (LogProfile) listofobj.get(0);
				LogProfile end = (LogProfile) listofobj.get(1);

				Long dif = Long.parseLong(end.getTimestamp()) - Long.parseLong(start.getTimestamp());

				end.setDuration(dif);
				if (dif > 4) {
					end.setAlert(true);
				}

				return end;
			}).filter(logp -> logp.getDuration() > 0).collect(Collectors.toList());

		} catch (IOException e) {
		
			
			log.error(e.getMessage());
			log.error(e.getCause());
			e.printStackTrace();
		}
		log.debug(" Log Processing completed successfully");
		
		log.debug("Storing data to database");
		
		logproList.forEach(log -> logprdao.save(log));

		logprdao.getAll().forEach(log::debug);

	}

}