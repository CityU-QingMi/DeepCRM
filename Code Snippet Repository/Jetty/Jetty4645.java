    @Test
    public void testFindInis() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("hb.1/home");
        Path homePath = homeDir.toPath().toAbsolutePath();
        Path basePath = testdir.getEmptyPathDir();

        PathFinder finder = new PathFinder();
        finder.setFileMatcher("glob:**/*.ini");
        finder.setBase(homePath);

        Files.walkFileTree(homePath,EnumSet.of(FileVisitOption.FOLLOW_LINKS),30,finder);

        List<String> expected = new ArrayList<>();
        expected.add("${jetty.home}/start.d/jmx.ini");
        expected.add("${jetty.home}/start.d/jndi.ini");
        expected.add("${jetty.home}/start.d/jsp.ini");
        expected.add("${jetty.home}/start.d/logging.ini");
        expected.add("${jetty.home}/start.d/ssl.ini");
        expected.add("${jetty.home}/start.ini");
        FSTest.toOsSeparators(expected);

        BaseHome hb = new BaseHome(new String[] { "jetty.home=" + homePath.toString(), "jetty.base=" + basePath.toString() });
        BaseHomeTest.assertPathList(hb,"Files found",expected,finder);
    }
