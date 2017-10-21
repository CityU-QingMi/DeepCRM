    public void initialize(BaseHome baseHome, CommandLineConfigSource cmdLineSource) throws IOException
    {
        String dbgProp = cmdLineSource.getProperty("debug");
        if (dbgProp != null)
        {
            debug = Boolean.parseBoolean(dbgProp);
        }

        String logFileName = cmdLineSource.getProperty("start-log-file");

        for (RawArgs.Entry arg : cmdLineSource.getArgs())
        {
            if ("--debug".equals(arg.getLine()))
            {
                debug = true;
                continue;
            }

            if (arg.startsWith("--start-log-file"))
            {
                logFileName = Props.getValue(arg.getLine());
                continue;
            }
        }

        if (logFileName != null)
        {
            Path logfile = baseHome.getPath(logFileName);
            logfile = logfile.toAbsolutePath();
            initLogFile(logfile);
        }
    }
