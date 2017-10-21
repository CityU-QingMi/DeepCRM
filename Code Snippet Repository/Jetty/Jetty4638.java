    @Test
    public void testJettyHomeWithSpaces() throws Exception
    {
        Path distPath = MavenTestingUtils.getTestResourceDir("dist-home").toPath().toRealPath();
        Path homePath = MavenTestingUtils.getTargetTestingPath().resolve("dist home with spaces");
        IO.copy(distPath.toFile(),homePath.toFile());
        homePath.resolve("lib/a library.jar").toFile().createNewFile();

        List<String> cmdLineArgs = new ArrayList<>();
        cmdLineArgs.add("user.dir=" + homePath);
        cmdLineArgs.add("jetty.home=" + homePath);
        cmdLineArgs.add("--lib=lib/a library.jar");

        Main main = new Main();
        StartArgs args = main.processCommandLine(cmdLineArgs.toArray(new String[cmdLineArgs.size()]));
        BaseHome baseHome = main.getBaseHome();

        assertThat("jetty.home",baseHome.getHome(),is(homePath.toString()));
        assertThat("jetty.base",baseHome.getBase(),is(homePath.toString()));

        ConfigurationAssert.assertConfiguration(baseHome,args,"assert-home-with-spaces.txt");
    }
