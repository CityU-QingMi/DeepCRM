    private FrameworkLogger(String s) {

        if (!noopMode) {
            if (log4jGetLogger == null) {
                jdkLogger = Logger.getLogger(s);
            } else {
                try {
                    log4jLogger = log4jGetLogger.invoke(null,
                                                        new Object[]{ s });
                } catch (Exception e) {
                    throw new RuntimeException(
                        "Failed to instantiate Log4j Logger", e);
                }
            }
        }

        synchronized (FrameworkLogger.class) {
            loggerInstances.put(s, this);
        }
    }
