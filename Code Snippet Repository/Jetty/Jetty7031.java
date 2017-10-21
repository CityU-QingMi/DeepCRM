    private int setLogLevel(Class<?> clazz, int newLevel)
    {
        int oldLevel = StdErrLog.LEVEL_DEFAULT;
        Logger logger = Log.getLogger(clazz);
        if (logger instanceof StdErrLog)
        {
            StdErrLog stdErrLog = (StdErrLog) logger;
            oldLevel = stdErrLog.getLevel();
            stdErrLog.setLevel(newLevel);
        }
        
        return oldLevel;
    }
