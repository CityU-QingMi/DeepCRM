    @Override
    public void log(final LogRecord record) {
        if (isFiltered(record)) {
            return;
        }
        final org.apache.logging.log4j.Level level = LevelTranslator.toLevel(record.getLevel());
        final Object[] parameters = record.getParameters();
        final MessageFactory messageFactory = logger.getMessageFactory();
        final Message message = parameters == null ?
            messageFactory.newMessage(record.getMessage()) /* LOG4J2-1251: not formatted case */ :
            messageFactory.newMessage(record.getMessage(), parameters);
        final Throwable thrown = record.getThrown();
        logger.logIfEnabled(FQCN, level, null, message, thrown);
    }
