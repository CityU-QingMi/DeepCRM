    @Test
    public void testGetPaths_OnlyHome() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("hb.1/home");

        ConfigSources config = new ConfigSources();
        config.add(new JettyHomeConfigSource(homeDir.toPath()));

        BaseHome hb = new BaseHome(config);
        List<Path> paths = hb.getPaths("start.d/*");

        List<String> expected = new ArrayList<>();
        expected.add("${jetty.home}/start.d/jmx.ini");
        expected.add("${jetty.home}/start.d/jndi.ini");
        expected.add("${jetty.home}/start.d/jsp.ini");
        expected.add("${jetty.home}/start.d/logging.ini");
        expected.add("${jetty.home}/start.d/ssl.ini");
        FSTest.toOsSeparators(expected);

        assertPathList(hb,"Paths found",expected,paths);
    }
