    public void testAccessPrimitiveInt() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        sma.setExcludedPackageNames(TextParseUtil.commaDelimitedStringToSet("java.lang.,ognl,javax"));

        String propertyName = "intField";
        Member member = FooBar.class.getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue(accessible);
    }
