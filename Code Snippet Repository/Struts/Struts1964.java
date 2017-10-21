    public void testNoStripExpressionIfNoAltSyntax() throws Exception {
        // given
        loadConfigurationProviders(new MockConfigurationProvider());
        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
        String anExpression = "%{foo}";

        // when
        String actual = ComponentUtils.stripExpressionIfAltSyntax(stack, anExpression);

        // then
        assertEquals(actual, "%{foo}");
    }
