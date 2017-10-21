    public void testAccessMemberAccessIsBlocked() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        Set<Class<?>> excluded = new HashSet<Class<?>>();
        excluded.add(SecurityMemberAccess.class);
        sma.setExcludedClasses(excluded);

        String propertyName = "excludedClasses";
        String setter = "setExcludedClasses";
        Member member = SecurityMemberAccess.class.getMethod(setter, Set.class);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertFalse(accessible);
    }
