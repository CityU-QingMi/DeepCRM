    private Level nearestLevel(final java.util.logging.Level customJavaLevel) {
        long prevDist = Long.MAX_VALUE;
        java.util.logging.Level prevLevel = null;
        for (final java.util.logging.Level mappedJavaLevel : sortedJulLevels) {
            final long distance = distance(customJavaLevel, mappedJavaLevel);
            if (distance > prevDist) {
                return julToLog4j.get(prevLevel);
            }
            prevDist = distance;
            prevLevel = mappedJavaLevel;
        }
        return julToLog4j.get(prevLevel);
    }
