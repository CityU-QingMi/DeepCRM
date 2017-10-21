    public void init(String[] args) throws Exception
    {
        try
        {
            jsvcStartArgs = processCommandLine(args);
        }
        catch (UsageException e)
        {
            StartLog.error(e.getMessage());
            usageExit(e.getCause(),e.getExitCode(),false);
        }
        catch (Throwable e)
        {
            usageExit(e,UsageException.ERR_UNKNOWN,false);
        }
    }
