    public void testAccessStatic() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(true);
        sma.setExcludedClasses(new HashSet<Class<?>>(Collections.singletonList(Class.class)));

        // when
        Member method = StaticTester.class.getMethod("sayHello");
        boolean actual = sma.isAccessible(context, Class.class, method, null);

        // then
        assertTrue("Access to static is blocked!", actual);
    }
