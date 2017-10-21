    @Test
    public void testRefCommon() throws Exception
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
                "jetty.http.host=127.0.0.1",//
                "--include-jetty-dir=" + common.toString());

        ConfigSources sources = new ConfigSources();
        
        String cmdLine[] = new String[0];
        sources.add(new CommandLineConfigSource(cmdLine));
        sources.add(new JettyHomeConfigSource(home));
        sources.add(new JettyBaseConfigSource(base));

        assertIdOrder(sources,"<command-line>","${jetty.base}",common.toString(),"${jetty.home}");

        assertDirOrder(sources,base,common,home);

        assertProperty(sources,"jetty.http.host","127.0.0.1");
        assertProperty(sources,"jetty.http.port","8080"); // from 'common'
    }
