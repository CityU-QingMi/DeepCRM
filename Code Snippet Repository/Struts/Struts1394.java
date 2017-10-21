    public void testPackageNameExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Set<String> excluded = new HashSet<String>();
        excluded.add(FooBar.class.getPackage().getName());
        sma.setExcludedPackageNames(excluded);

        String propertyName = "stringField";
        Member member = FooBar.class.getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));

        // when
        boolean actual = sma.isAccessible(context, target, member, propertyName);

        // then
        assertFalse("stringField is accessible!", actual);
    }
