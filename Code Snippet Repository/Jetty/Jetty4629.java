    @Test
    public void testCommandLine_1Extra_FromPropPrefix() throws Exception
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create opt
        Path opt = testdir.getPathFile("opt");
        FS.ensureEmpty(opt);

        // Create common
        Path common = opt.resolve("common");
        FS.ensureEmpty(common);
        TestEnv.makeFile(common,"start.ini","jetty.http.port=8080");

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1");

        String dirRef = "${my.opt}" + File.separator + "common";

        // Simple command line reference to include-jetty-dir via property (also on command line)
        MainResult result = runMain(base,home,
        // property to 'opt' dir
                "my.opt=" + opt.toString(),
                // reference via property prefix
                "--include-jetty-dir=" + dirRef);

        List<String> expectedSearchOrder = new ArrayList<>();
        expectedSearchOrder.add("${jetty.base}");
        expectedSearchOrder.add(dirRef); // should use property
        expectedSearchOrder.add("${jetty.home}");
        result.assertSearchOrder(expectedSearchOrder);

        result.assertProperty("jetty.http.host","127.0.0.1");
        result.assertProperty("jetty.http.port","8080"); // from 'common'
    }
