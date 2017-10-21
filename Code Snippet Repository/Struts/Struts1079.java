    public void testLiteralMethods() throws Exception {
        // given
        String method = "myMethod";
        Set<String> literals = new HashSet<>();
        literals.add(method);

        // when
        AllowedMethods allowedMethods = AllowedMethods.build(false, literals, ActionConfig.DEFAULT_METHOD_REGEX);

        // then
        assertEquals(1, allowedMethods.list().size());
        assertTrue(allowedMethods.isAllowed(method));
        assertFalse(allowedMethods.isAllowed("someOtherMethod"));
    }
