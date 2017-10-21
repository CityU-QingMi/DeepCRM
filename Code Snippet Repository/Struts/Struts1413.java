    public void testPackageExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Set<Pattern> excluded = new HashSet<Pattern>();
        excluded.add(Pattern.compile("^" + FooBar.class.getPackage().getName().replaceAll("\\.", "\\\\.") + ".*"));
        sma.setExcludedPackageNamePatterns(excluded);

        String propertyName = "stringField";
        Member member = FooBar.class.getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));

        // when
        boolean actual = sma.isAccessible(context, target, member, propertyName);

        // then
        assertFalse("stringField is accessible!", actual);
    }
