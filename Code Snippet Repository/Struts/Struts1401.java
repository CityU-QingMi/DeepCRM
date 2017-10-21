    public void testAllowStaticAccessIfClassIsNotExcluded() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(true);
        sma.setExcludedClasses(new HashSet<Class<?>>(Collections.singletonList(ClassLoader.class)));

        // when
        Member method = Class.class.getMethod("getClassLoader");
        boolean actual = sma.isAccessible(context, Class.class, method, null);

        // then
        assertTrue("Invalid test! Access to static method of excluded class is blocked!", actual);
    }
