    public void testAltSyntaxIsTrue() throws Exception {
        // given
        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();

        // when
        boolean actual = ComponentUtils.altSyntax(stack);

        // then
        assertTrue(actual);
    }
