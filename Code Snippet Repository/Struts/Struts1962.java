    public void testStripExpression() throws Exception {
        // given
        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
        String anExpression = "%{foo}";

        // when
        String actual = ComponentUtils.stripExpressionIfAltSyntax(stack, anExpression);

        // then
        assertEquals(actual, "foo");
    }
