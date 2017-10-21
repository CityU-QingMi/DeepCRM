            @Override
            String constructor() {
                //@formatter:off
                return "" 
                        + "%n" 
                        + "    private %s(final Logger logger) {%n" 
                        + "        this.logger = new ExtendedLoggerWrapper((AbstractLogger) logger, logger.getName(), "
                        + "logger.getMessageFactory());%n" 
                        + "    }%n";
                //@formatter:on
            }
