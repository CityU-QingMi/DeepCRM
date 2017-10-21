    @Test
    public void testGetPaths_Both() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("hb.1/home");
        File baseDir = MavenTestingUtils.getTestResourceDir("hb.1/base");

        ConfigSources config = new ConfigSources();
        config.add(new JettyBaseConfigSource(baseDir.toPath()));
        config.add(new JettyHomeConfigSource(homeDir.toPath()));

        BaseHome hb = new BaseHome(config);
        List<Path> paths = hb.getPaths("start.d/*.ini");

        List<String> expected = new ArrayList<>();
        expected.add("${jetty.base}/start.d/jmx.ini");
        expected.add("${jetty.home}/start.d/jndi.ini");
        expected.add("${jetty.home}/start.d/jsp.ini");
        expected.add("${jetty.base}/start.d/logging.ini");
        expected.add("${jetty.home}/start.d/ssl.ini");
        expected.add("${jetty.base}/start.d/myapp.ini");
        FSTest.toOsSeparators(expected);

        assertPathList(hb,"Paths found",expected,paths);
    }
