    public void testJavaxServletPackageExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Set<Pattern> excluded = new HashSet<Pattern>();
        excluded.add(Pattern.compile("^javax\\..+"));
        sma.setExcludedPackageNamePatterns(excluded);

        String propertyName = "value";
        Member member = TagSupport.class.getMethod("doStartTag");

        // when
        boolean actual = sma.isAccessible(context, new TestAction(), member, propertyName);

        // then
        assertFalse("javax.servlet package is accessible!", actual);
    }
