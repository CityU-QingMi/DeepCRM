    @Test
    public void testWithCommandLine() throws Exception
    {
        List<String> cmdLineArgs = new ArrayList<>();

        Path homePath = MavenTestingUtils.getTestResourceDir("dist-home").toPath().toRealPath();
        cmdLineArgs.add("jetty.home=" + homePath.toString());
        cmdLineArgs.add("user.dir=" + homePath.toString());

        // JVM args
        cmdLineArgs.add("--exec");
        cmdLineArgs.add("-Xms1024m");
        cmdLineArgs.add("-Xmx1024m");

        // Arbitrary Libs
        Path extraJar = MavenTestingUtils.getTestResourceFile("extra-libs/example.jar").toPath().toRealPath();
        Path extraDir = MavenTestingUtils.getTestResourceDir("extra-resources").toPath().toRealPath();
        
        assertThat("Extra Jar exists: " + extraJar,Files.exists(extraJar),is(true));
        assertThat("Extra Dir exists: " + extraDir,Files.exists(extraDir),is(true));
        
        StringBuilder lib = new StringBuilder();
        lib.append("--lib=");
        lib.append(extraJar.toString());
        lib.append(File.pathSeparator);
        lib.append(extraDir.toString());
        
        cmdLineArgs.add(lib.toString());

        // Arbitrary XMLs
        cmdLineArgs.add("config.xml");
        cmdLineArgs.add("config-foo.xml");
        cmdLineArgs.add("config-bar.xml");

        Main main = new Main();

        StartArgs args = main.processCommandLine(cmdLineArgs.toArray(new String[cmdLineArgs.size()]));
        BaseHome baseHome = main.getBaseHome();

        assertThat("jetty.home",baseHome.getHome(),is(homePath.toString()));
        assertThat("jetty.base",baseHome.getBase(),is(homePath.toString()));

        ConfigurationAssert.assertConfiguration(baseHome,args,"assert-home-with-jvm.txt");
    }
