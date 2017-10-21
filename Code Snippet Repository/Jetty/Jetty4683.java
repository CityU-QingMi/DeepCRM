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

        ConfigSources sources = new ConfigSources();

        // Simple command line reference to include-jetty-dir via property (also on command line)
        String[] cmdLine = new String[] {
                // property to 'opt' dir
                "my.opt=" + opt.toString(),
                // reference via property prefix
                "--include-jetty-dir=" + dirRef };
        
        sources.add(new CommandLineConfigSource(cmdLine));
        sources.add(new JettyHomeConfigSource(home));
        sources.add(new JettyBaseConfigSource(base));

        assertIdOrder(sources,"<command-line>","${jetty.base}",dirRef,"${jetty.home}");

        assertDirOrder(sources,base,common,home);

        assertProperty(sources,"jetty.http.host","127.0.0.1");
        assertProperty(sources,"jetty.http.port","8080"); // from 'common'
    }
