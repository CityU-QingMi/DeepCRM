    public void testObjectOverwrittenMethodsExclusion() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        String propertyName = "hashCode";
        Member member = FooBar.class.getMethod(propertyName);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue("hashCode() from FooBar isn't accessible!!!", accessible);
    }
