    @Test
    public void testGetPath_OnlyHome() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("hb.1/home");
        
        ConfigSources config = new ConfigSources();
        config.add(new JettyHomeConfigSource(homeDir.toPath()));

        BaseHome hb = new BaseHome(config);
        Path startIni = hb.getPath("start.ini");

        String ref = hb.toShortForm(startIni);
        Assert.assertThat("Reference",ref,startsWith("${jetty.home}"));

        String contents = IO.readToString(startIni.toFile());
        Assert.assertThat("Contents",contents,containsString("Home Ini"));
    }
