    public void testContainsExpressionIsFalse() throws Exception {
        // given
        String anExpression = "foo";

        // when
        boolean actual = ComponentUtils.containsExpression(anExpression);

        // then
        assertFalse(actual);
    }
