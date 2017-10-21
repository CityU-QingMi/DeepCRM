    public static void main(String[] args)
    {
        boolean test=false;
        try
        {
            Main main = new Main();
            StartArgs startArgs = main.processCommandLine(args);
            test=startArgs.isTestingModeEnabled();
            main.start(startArgs);
        }
        catch (UsageException e)
        {
            StartLog.error(e.getMessage());
            usageExit(e.getCause(),e.getExitCode(),test);
        }
        catch (Throwable e)
        {
            usageExit(e,UsageException.ERR_UNKNOWN,test);
        }
    }
