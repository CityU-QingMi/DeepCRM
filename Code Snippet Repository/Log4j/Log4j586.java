    public Logger getParent() {
        final LoggerConfig lc = privateConfig.loggerConfig.getName().equals(getName()) ? privateConfig.loggerConfig
                .getParent() : privateConfig.loggerConfig;
        if (lc == null) {
            return null;
        }
        final String lcName = lc.getName();
        final MessageFactory messageFactory = getMessageFactory();
        if (context.hasLogger(lcName, messageFactory)) {
            return context.getLogger(lcName, messageFactory);
        }
        return new Logger(context, lcName, messageFactory);
    }
