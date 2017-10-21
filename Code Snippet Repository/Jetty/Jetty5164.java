    public StacklessLogging(Logger... logs)
    {
        for (Logger log : logs)
        {
            // only operate on loggers that are of type StdErrLog
            if (log instanceof StdErrLog && !log.isDebugEnabled())
            {
                StdErrLog stdErrLog=((StdErrLog)log);
                if (!stdErrLog.isHideStacks())
                {
                    stdErrLog.setHideStacks(true);
                    squelched.add(stdErrLog);
                }
            }
        }
    }
