    @Test
    public void testBasicProcessing() throws Exception
    {
        List<String> cmdLineArgs = new ArrayList<>();
        Path testJettyHome = MavenTestingUtils.getTestResourceDir("dist-home").toPath().toRealPath();
        cmdLineArgs.add("user.dir=" + testJettyHome);
        cmdLineArgs.add("jetty.home=" + testJettyHome);
        // cmdLineArgs.add("jetty.http.port=9090");

        Main main = new Main();
        StartArgs args = main.processCommandLine(cmdLineArgs.toArray(new String[cmdLineArgs.size()]));
        BaseHome baseHome = main.getBaseHome();
        // System.err.println(args);

        ConfigurationAssert.assertConfiguration(baseHome,args,"assert-home.txt");

        // System.err.println("StartArgs.props:");
        // args.getProperties().forEach(p->System.err.println(p));
        // System.err.println("BaseHome.props:");
        // baseHome.getConfigSources().getProps().forEach(p->System.err.println(p));
        
        assertThat(args.getProperties().getString("jetty.home"),is(baseHome.getHome()));
        assertThat(args.getProperties().getString("jetty.home.uri")+"/",is(baseHome.getHomePath().toUri().toString()));
        assertThat(args.getProperties().getString("jetty.base"),is(baseHome.getBase()));
        assertThat(args.getProperties().getString("jetty.base.uri")+"/",is(baseHome.getBasePath().toUri().toString()));
    }
