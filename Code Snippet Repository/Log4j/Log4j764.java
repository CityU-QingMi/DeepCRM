    @Override
    public LogEvent rewrite(final LogEvent event) {
        if (event.getLoggerName() == null || !event.getLoggerName().startsWith(loggerName)) {
            return event;
        }
        final Level sourceLevel = event.getLevel();
        final Level newLevel = map.get(sourceLevel);
        if (newLevel == null || newLevel == sourceLevel) {
            return event;
        }
        final LogEvent result = new Log4jLogEvent.Builder(event).setLevel(newLevel).build();
        return result;
    }
