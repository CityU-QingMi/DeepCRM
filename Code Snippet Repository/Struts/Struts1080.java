    public void testWildcardMethodsWithNoSMI() throws Exception {
        // given
        String method = "my{1}";
        Set<String> literals = new HashSet<>();
        literals.add(method);

        // when
        AllowedMethods allowedMethods = AllowedMethods.build(false, literals, ActionConfig.DEFAULT_METHOD_REGEX);

        // then
        assertEquals(1, allowedMethods.list().size());
        assertTrue(allowedMethods.isAllowed("myMethod"));
        assertFalse(allowedMethods.isAllowed("someOtherMethod"));
    }
