    @Test
    public void testOrder_With1ExtraConfig() throws IOException
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create common
        Path common = testdir.getPathFile("common");
        FS.ensureEmpty(common.toFile());
        common = common.toRealPath();

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1",//
                "--include-jetty-dir=" + common.toString());

        ConfigSources sources = new ConfigSources();

        String[] cmdLine = new String[0];
        sources.add(new CommandLineConfigSource(cmdLine));
        sources.add(new JettyHomeConfigSource(home.toRealPath()));
        sources.add(new JettyBaseConfigSource(base.toRealPath()));

        assertIdOrder(sources,"<command-line>","${jetty.base}",common.toString(),"${jetty.home}");
    }
