    public void testAccessPrimitiveDoubleWithPackageRegExs() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        Set<Pattern> patterns = new HashSet<Pattern>();
        patterns.add(Pattern.compile("^java\\.lang\\..*"));
        sma.setExcludedPackageNamePatterns(patterns);

        String propertyName = "doubleValue";
        Member member = Double.class.getMethod(propertyName);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue(accessible);
    }
