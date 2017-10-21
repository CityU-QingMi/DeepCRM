    private void buildAppender(final String appenderName, final String appenderClass) {
        switch (appenderClass) {
        case "org.apache.log4j.ConsoleAppender":
            buildConsoleAppender(appenderName);
            break;
        case "org.apache.log4j.FileAppender":
            buildFileAppender(appenderName);
            break;
        case "org.apache.log4j.DailyRollingFileAppender":
            buildDailyRollingFileAppender(appenderName);
            break;
        case "org.apache.log4j.RollingFileAppender":
            buildRollingFileAppender(appenderName);
            break;
        case "org.apache.log4j.varia.NullAppender":
            buildNullAppender(appenderName);
            break;
        default:
            reportWarning("Unknown appender class: " + appenderClass + "; ignoring appender: " + appenderName);
        }
    }
