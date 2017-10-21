    private StatusLogger(final String name, final MessageFactory messageFactory) {
        super(name, messageFactory);
        this.logger = new SimpleLogger("StatusLogger", Level.ERROR, false, true, false, false, Strings.EMPTY,
                messageFactory, PROPS, System.err);
        this.listenersLevel = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();

        // LOG4J2-1813 if system property "log4j2.debug" is defined, print all status logging
        if (isDebugPropertyEnabled()) {
            logger.setLevel(Level.TRACE);
        }
    }
