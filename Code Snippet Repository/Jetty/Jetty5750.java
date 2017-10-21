    private String levelToString(int level)
    {
        switch (level)
        {
            case StdErrLog.LEVEL_ALL:
                return "ALL";
            case StdErrLog.LEVEL_DEBUG:
                return "DEBUG";
            case StdErrLog.LEVEL_INFO:
                return "INFO";
            case StdErrLog.LEVEL_WARN:
                return "WARN";
            default:
                return Integer.toString(level);
        }
    }
