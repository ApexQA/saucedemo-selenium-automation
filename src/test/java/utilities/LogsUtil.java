package utilities;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogsUtil {
    public static final String LOGS_PATH = "test-outputs/Logs";

    private LogsUtil() {
        super();
    }

    public static Logger logger() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    //LogUtils.info("This is a test log message", "This is another test log message")
    //Array of String
    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

}
