    public void testMiddleOfInheritanceExclusion4() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        String propertyName = "barLogic";
        Member member = BarInterface.class.getMethod(propertyName);

        Set<Class<?>> excluded = new HashSet<Class<?>>();
        excluded.add(FooBarInterface.class);
        sma.setExcludedClasses(excluded);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertFalse("barLogic() from BarInterface is accessible!!!", accessible);
    }
