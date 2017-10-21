    public void testAltSyntaxIsFalse() throws Exception {
        // given
        loadConfigurationProviders(new MockConfigurationProvider());
        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();

        // when
        boolean actual = ComponentUtils.altSyntax(stack);

        // then
        assertFalse(actual);
    }
