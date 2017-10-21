    @Test
    public void testOrder_BasicConfig() throws IOException
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1");

        ConfigSources sources = new ConfigSources();

        String[] cmdLine = new String[0];
        sources.add(new CommandLineConfigSource(cmdLine));
        sources.add(new JettyBaseConfigSource(base));
        sources.add(new JettyHomeConfigSource(home));

        assertIdOrder(sources,"<command-line>","${jetty.base}","${jetty.home}");
    }
