    protected static int getLevelId(String levelSegment, String levelName)
    {
        if (levelName == null)
        {
            return -1;
        }
        String levelStr = levelName.trim();
        if ("ALL".equalsIgnoreCase(levelStr))
        {
            return LEVEL_ALL;
        }
        else if ("DEBUG".equalsIgnoreCase(levelStr))
        {
            return LEVEL_DEBUG;
        }
        else if ("INFO".equalsIgnoreCase(levelStr))
        {
            return LEVEL_INFO;
        }
        else if ("WARN".equalsIgnoreCase(levelStr))
        {
            return LEVEL_WARN;
        }
        else if ("OFF".equalsIgnoreCase(levelStr))
        {
            return LEVEL_OFF;
        }
    
        System.err.println("Unknown StdErrLog level [" + levelSegment + "]=[" + levelStr + "], expecting only [ALL, DEBUG, INFO, WARN, OFF] as values.");
        return -1;
    }
