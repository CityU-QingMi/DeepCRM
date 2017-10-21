    public void testMiddleOfInheritanceExclusion1() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        String propertyName = "fooLogic";
        Member member = FooBar.class.getMethod(propertyName);

        Set<Class<?>> excluded = new HashSet<Class<?>>();
        excluded.add(BarInterface.class);
        sma.setExcludedClasses(excluded);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue("fooLogic() from FooInterface isn't accessible!!!", accessible);
    }
