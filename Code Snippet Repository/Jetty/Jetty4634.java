    @Test
    public void testStopProcessing() throws Exception
    {
        List<String> cmdLineArgs = new ArrayList<>();
        cmdLineArgs.add("--stop");
        cmdLineArgs.add("STOP.PORT=10000");
        cmdLineArgs.add("STOP.KEY=foo");
        cmdLineArgs.add("STOP.WAIT=300");

        Main main = new Main();
        StartArgs args = main.processCommandLine(cmdLineArgs.toArray(new String[cmdLineArgs.size()]));
        // System.err.println(args);

        // Assert.assertEquals("--stop should not build module tree", 0, args.getEnabledModules().size());
        assertEquals("--stop missing port","10000",args.getProperties().getString("STOP.PORT"));
        assertEquals("--stop missing key","foo",args.getProperties().getString("STOP.KEY"));
        assertEquals("--stop missing wait","300",args.getProperties().getString("STOP.WAIT"));
    }
