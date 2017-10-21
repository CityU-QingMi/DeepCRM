    public void enduserlog(Level level, String message) {

/**/
/**/
/**/
/**/
        if (noopMode) {
            return;
        }

        if (log4jLogger == null) {
            String c = FrameworkLogger.class.getName();
            String m = "\\l";

            jdkLogger.logp(level, c, m, message);
        } else {
            try {
                log4jLogMethod.invoke(log4jLogger, callerFqcnAvailable
                        ? new Object[] {
                           FrameworkLogger.class.getName(),
                           jdkToLog4jLevels.get(level), message, null}
                        : new Object[] {
                           jdkToLog4jLevels.get(level), message, null}
                );

                // Test where SqlFile correct here.
            } catch (Exception e) {
                throw new RuntimeException(
                    "Logging failed when attempting to log: " + message, e);
            }
        }
    }
