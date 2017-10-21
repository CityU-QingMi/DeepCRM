            @Override
            String constructor() {
                //@formatter:off
                return "" 
                        + "%n" 
                        + "    private %s(final Logger logger) {%n" 
                        + "        super((AbstractLogger) logger, logger.getName(), logger.getMessageFactory());%n" 
                        + "        this.logger = this;%n" 
                        + "    }%n";
                //@formatter:on
            }
