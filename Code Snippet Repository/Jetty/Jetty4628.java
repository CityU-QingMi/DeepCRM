    @Test
    public void testCommandLine_1Extra_FromSimpleProp() throws Exception
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create common
        Path common = testdir.getPathFile("common");
        FS.ensureEmpty(common);
        TestEnv.makeFile(common,"start.ini","jetty.http.port=8080");

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1");

        // Simple command line reference to include-jetty-dir via property (also on command line)
        MainResult result = runMain(base,home,
        // property
                "my.common=" + common.toString(),
                // reference via property
                "--include-jetty-dir=${my.common}");

        List<String> expectedSearchOrder = new ArrayList<>();
        expectedSearchOrder.add("${jetty.base}");
        expectedSearchOrder.add("${my.common}"); // should see property use
        expectedSearchOrder.add("${jetty.home}");
        result.assertSearchOrder(expectedSearchOrder);

        result.assertProperty("jetty.http.host","127.0.0.1");
        result.assertProperty("jetty.http.port","8080"); // from 'common'
    }
