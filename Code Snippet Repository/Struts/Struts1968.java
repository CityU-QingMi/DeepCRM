    public void testIsExpressionIsFalseWhenCombined() throws Exception {
        // given
        String anExpression = "bar%{foo}";

        // when
        boolean actual = ComponentUtils.isExpression(anExpression);

        // then
        assertFalse(actual);
    }
