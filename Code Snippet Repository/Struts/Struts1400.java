    public void testBlockStaticAccessIfClassIsExcluded() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        sma.setExcludedClasses(new HashSet<Class<?>>(Collections.singletonList(Class.class)));

        // when
        Member method = Class.class.getMethod("getClassLoader");
        boolean actual = sma.isAccessible(context, Class.class, method, null);

        // then
        assertFalse("Access to static method of excluded class isn't blocked!", actual);
    }
