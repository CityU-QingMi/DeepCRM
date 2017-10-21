    public void testDefaultPackageExclusion2() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Set<Pattern> excluded = new HashSet<Pattern>();
        excluded.add(Pattern.compile("^$"));
        sma.setExcludedPackageNamePatterns(excluded);
        
        // when
        boolean actual = sma.isPackageExcluded(null, null);

        // then
        assertTrue("default package isn't excluded!", actual);
    }
