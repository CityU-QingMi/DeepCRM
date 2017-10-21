    @Test
    public void testFindMods() throws IOException
    {
        File homeDir = MavenTestingUtils.getTestResourceDir("dist-home");
        Path homePath = homeDir.toPath().toAbsolutePath();
        Path basePath = testdir.getEmptyPathDir();

        List<String> expected = new ArrayList<>();
        File modulesDir = new File(homeDir,"modules");
        for (File file : modulesDir.listFiles())
        {
            if (file.getName().endsWith(".mod"))
            {
                expected.add("${jetty.home}/modules/" + file.getName());
            }
        }
        FSTest.toOsSeparators(expected);
        
        Path modulesPath = modulesDir.toPath();

        PathFinder finder = new PathFinder();
        finder.setFileMatcher(PathMatchers.getMatcher("modules/*.mod"));
        finder.setBase(modulesPath);
        
        Files.walkFileTree(modulesPath,EnumSet.of(FileVisitOption.FOLLOW_LINKS),1,finder);

        BaseHome hb = new BaseHome(new String[] { "jetty.home=" + homePath.toString(), "jetty.base=" + basePath.toString() });
        BaseHomeTest.assertPathList(hb,"Files found",expected,finder);
    }
