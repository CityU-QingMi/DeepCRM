    @Override
    public Level toLevel(final java.util.logging.Level javaLevel) {
        if (javaLevel == null) {
            return null;
        }
        final Level level = julToLog4j.get(javaLevel);
        if (level != null) {
            return level;
        }
        final Level nearestLevel = nearestLevel(javaLevel);
        julToLog4j.put(javaLevel, nearestLevel);
        return nearestLevel;
    }
