    private static void createLoggerFactory() {
        String userLoggerFactory = System.getProperty(XWorkConstants.XWORK_LOGGER_FACTORY);
        if (userLoggerFactory != null) {
            try {
                Class clazz = Class.forName(userLoggerFactory);
                factory = (LoggerFactory) clazz.newInstance();
            } catch (Exception e) {
                throw new XWorkException("System property [" + XWorkConstants.XWORK_LOGGER_FACTORY +
                        "] was defined as [" + userLoggerFactory + "] but there is a problem to use that LoggerFactory!", e);
            }
        } else {
            factory = new JdkLoggerFactory();
            for (LoggerClass logger : loggers) {
                if (logger.isSupported()) {
                    factory = logger.createInstance();
                    break;
                }
            }
        }
    }
