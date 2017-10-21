    @Test
    public void testGetPath_Both() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("hb.1/home");
        File baseDir = MavenTestingUtils.getTestResourceDir("hb.1/base");

        ConfigSources config = new ConfigSources();
        config.add(new JettyBaseConfigSource(baseDir.toPath()));
        config.add(new JettyHomeConfigSource(homeDir.toPath()));

        BaseHome hb = new BaseHome(config);
        Path startIni = hb.getPath("start.ini");

        String ref = hb.toShortForm(startIni);
        Assert.assertThat("Reference",ref,startsWith("${jetty.base}"));

        String contents = IO.readToString(startIni.toFile());
        Assert.assertThat("Contents",contents,containsString("Base Ini"));
    }
