    public void testContainsExpressionIsTrue() throws Exception {
        // given
        String anExpression = "%{foo}";

        // when
        boolean actual = ComponentUtils.containsExpression(anExpression);

        // then
        assertTrue(actual);
    }
