    public void testDefaultPackageExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Set<Pattern> excluded = new HashSet<Pattern>();
        excluded.add(Pattern.compile("^" + FooBar.class.getPackage().getName().replaceAll("\\.", "\\\\.") + ".*"));
        sma.setExcludedPackageNamePatterns(excluded);
        
        // when
        boolean actual = sma.isPackageExcluded(null, null);

        // then
        assertFalse("default package is excluded!", actual);
    }
