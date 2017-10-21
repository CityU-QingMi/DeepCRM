    private MainResult runMain(Path baseDir, Path homeDir, String... cmdLineArgs) throws Exception
    {
        MainResult ret = new MainResult();
        ret.main = new Main();
        List<String> cmdLine = new ArrayList<>();
        cmdLine.add("jetty.home=" + homeDir.toString());
        cmdLine.add("jetty.base=" + baseDir.toString());
        // cmdLine.add("--debug");
        for (String arg : cmdLineArgs)
        {
            cmdLine.add(arg);
        }
        ret.args = ret.main.processCommandLine(cmdLine);
        return ret;
    }
