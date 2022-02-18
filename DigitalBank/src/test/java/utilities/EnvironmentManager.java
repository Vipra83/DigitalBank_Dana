package utilities;

public class EnvironmentManager {
    public static String baseUrl;
    public static String username;
    public static String password;

    public static void setUpEnvironment() throws Exception {
        switch (ConfigReader.getProperty("environment").toLowerCase()) {
            case "dev":
                DEV();
                break;
            case "qa":
                QA();
                break;
            case "prod":
                PROD();
                break;
            default:
                throw new Exception("Invalid environment in configuration.properties.\n" +
                        "Please select from DEV, QA, PROD");
        }
    }

    private static void DEV() {
        baseUrl = "http://3.20.225.112:8080/bank";
    }

    private static void QA() {
        baseUrl = "http://3.20.225.112:8080/bank";
    }

    private static void PROD() {
        baseUrl = "http://3.131.35.165:8080/bank";
        username = ConfigReader.getProperty("username");
        password = ConfigReader.getProperty("password");;
    }
}
