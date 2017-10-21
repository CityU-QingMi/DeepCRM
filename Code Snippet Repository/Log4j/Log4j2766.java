    public DefaultLevelConverter() {
        // Map JUL to Log4j
        mapJulToLog4j(java.util.logging.Level.ALL, Level.ALL);
        mapJulToLog4j(java.util.logging.Level.FINEST, LevelTranslator.FINEST);
        mapJulToLog4j(java.util.logging.Level.FINER, Level.TRACE);
        mapJulToLog4j(java.util.logging.Level.FINE, Level.DEBUG);
        mapJulToLog4j(java.util.logging.Level.CONFIG, LevelTranslator.CONFIG);
        mapJulToLog4j(java.util.logging.Level.INFO, Level.INFO);
        mapJulToLog4j(java.util.logging.Level.WARNING, Level.WARN);
        mapJulToLog4j(java.util.logging.Level.SEVERE, Level.ERROR);
        mapJulToLog4j(java.util.logging.Level.OFF, Level.OFF);
        // Map Log4j to JUL
        mapLog4jToJul(Level.ALL, java.util.logging.Level.ALL);
        mapLog4jToJul(LevelTranslator.FINEST, java.util.logging.Level.FINEST);
        mapLog4jToJul(Level.TRACE, java.util.logging.Level.FINER);
        mapLog4jToJul(Level.DEBUG, java.util.logging.Level.FINE);
        mapLog4jToJul(LevelTranslator.CONFIG, java.util.logging.Level.CONFIG);
        mapLog4jToJul(Level.INFO, java.util.logging.Level.INFO);
        mapLog4jToJul(Level.WARN, java.util.logging.Level.WARNING);
        mapLog4jToJul(Level.ERROR, java.util.logging.Level.SEVERE);
        mapLog4jToJul(Level.FATAL, java.util.logging.Level.SEVERE);
        mapLog4jToJul(Level.OFF, java.util.logging.Level.OFF);
        // Sorted Java levels
        sortedJulLevels.addAll(julToLog4j.keySet());
        Collections.sort(sortedJulLevels, new JulLevelComparator());

    }
