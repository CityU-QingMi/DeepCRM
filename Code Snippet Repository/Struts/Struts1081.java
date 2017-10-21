    public void testWildcardMethodsWithSMI() throws Exception {
        // given
        Set<String> literals = new HashSet<>();
        literals.add("my{1}");
        literals.add("myMethod");

        // when
        AllowedMethods allowedMethods = AllowedMethods.build(true, literals, ActionConfig.DEFAULT_METHOD_REGEX);

        // then
        assertEquals(1, allowedMethods.list().size());
        assertFalse(allowedMethods.isAllowed("my{1}"));
        assertTrue(allowedMethods.isAllowed("myMethod"));
        assertFalse(allowedMethods.isAllowed("someOtherMethod"));
    }
