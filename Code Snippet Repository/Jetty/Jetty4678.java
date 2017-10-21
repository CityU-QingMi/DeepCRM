    @Test
    public void testRefCommonRefCorp_CmdLineProp() throws Exception
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

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

        ConfigSources sources = new ConfigSources();
        
        String cmdLine[] = new String[]{
             // command line property should override all others
                "jetty.http.port=7070"
        };
        sources.add(new CommandLineConfigSource(cmdLine));
        sources.add(new JettyHomeConfigSource(home));
        sources.add(new JettyBaseConfigSource(base));

        assertIdOrder(sources,"<command-line>","${jetty.base}",
                common.toString(),
                corp.toString(),
                "${jetty.home}");

        assertDirOrder(sources,base,common,corp,home);

        assertProperty(sources,"jetty.http.host","127.0.0.1");
        assertProperty(sources,"jetty.http.port","7070"); // from <command-line>
    }
