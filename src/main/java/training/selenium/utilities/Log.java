package training.selenium.utilities;

import org.apache.log4j.Logger;

public class Log {

	//Initialize Log4j instance
	
	private static Logger log = Logger.getLogger(Log.class.getName());
	
	//starting tests
	public static void startLog(String testClassName) {
		log.info("Starting Testing ...");
	}
	
	//ending testing
	public static void endLog(String testClassName) {
		log.info("Ending Testing ...");
	}
	
	// info level logs
	public static void info(String message) {
		log.info(message);
	}
	
	// warn level logs
	public static void warm(String message) {
		log.warn(message);
	}
	
	// fatal level logs
	public static void fatal(String message) {
		log.fatal(message);
	}
	
	// debug level logs
	public static void debug(String message) {
		log.debug(message);
	}
}
