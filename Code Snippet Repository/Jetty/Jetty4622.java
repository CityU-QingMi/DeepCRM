    @Test
    public void testRefCommonRefCorp_CmdLineRef() throws Exception
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create devops
        Path devops = testdir.getPathFile("devops");
        FS.ensureEmpty(devops);
        TestEnv.makeFile(devops,"start.ini", //
                "--module=optional", //
                "jetty.http.port=2222");

        // Create corp
        Path corp = testdir.getPathFile("corp");
        FS.ensureEmpty(corp);
        TestEnv.makeFile(corp,"start.ini", //
                "jetty.http.port=9090");

        // Create common
        Path common = testdir.getPathFile("common");
        FS.ensureEmpty(common);
        TestEnv.makeFile(common,"start.ini", //
                "--include-jetty-dir=" + corp.toString(), //
                "jetty.http.port=8080");

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1",//
                "--include-jetty-dir=" + common.toString());

        MainResult result = runMain(base,home,
        // command line provided include-jetty-dir ref
                "--include-jetty-dir=" + devops.toString());

        List<String> expectedSearchOrder = new ArrayList<>();
        expectedSearchOrder.add("${jetty.base}");
        expectedSearchOrder.add(devops.toString());
        expectedSearchOrder.add(common.toString());
        expectedSearchOrder.add(corp.toString());
        expectedSearchOrder.add("${jetty.home}");
        result.assertSearchOrder(expectedSearchOrder);

        result.assertProperty("jetty.http.host","127.0.0.1");
        result.assertProperty("jetty.http.port","2222"); // from 'devops'
    }
