    public void testBlockStaticAccess() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        sma.setExcludedClasses(new HashSet<Class<?>>(Collections.singletonList(Class.class)));

        // when
        Member method = StaticTester.class.getMethod("sayHello");
        boolean actual = sma.isAccessible(context, Class.class, method, null);

        // then
        assertFalse("Access to static isn't blocked!", actual);
    }
