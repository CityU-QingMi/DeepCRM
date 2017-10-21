    public void testWithoutClassExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        String propertyName = "stringField";
        Member member = FooBar.class.getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue(accessible);
    }
