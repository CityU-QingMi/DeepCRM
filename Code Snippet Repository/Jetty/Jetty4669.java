    @Test
    @Ignore
    public void testBadConfig() throws Exception
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("dist-home");
        File baseDir = MavenTestingUtils.getTestResourceDir("usecases/" + caseName);

        Main main = new Main();
        List<String> cmdLine = new ArrayList<>();
        cmdLine.add("jetty.home=" + homeDir.getAbsolutePath());
        cmdLine.add("jetty.base=" + baseDir.getAbsolutePath());
        // cmdLine.add("--debug");

        if (commandLineArgs != null)
        {
            for (String arg : commandLineArgs)
            {
                cmdLine.add(arg);
            }
        }

        expectedException.expect(UsageException.class);
        expectedException.expectMessage(containsString(expectedErrorMessage));
        main.processCommandLine(cmdLine);
    }
