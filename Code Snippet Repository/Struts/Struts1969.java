    public void testIsExpressionIsFalse() throws Exception {
        // given
        String anExpression = "foo";

        // when
        boolean actual = ComponentUtils.isExpression(anExpression);

        // then
        assertFalse(actual);
    }
