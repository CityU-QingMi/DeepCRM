    public void testIsExpressionIsTrue() throws Exception {
        // given
        String anExpression = "%{foo}";

        // when
        boolean actual = ComponentUtils.isExpression(anExpression);

        // then
        assertTrue(actual);
    }
