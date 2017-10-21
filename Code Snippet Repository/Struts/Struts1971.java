    public void testIsContainsIsTrueWhenCombined() throws Exception {
        // given
        String anExpression = "bar%{foo}";

        // when
        boolean actual = ComponentUtils.containsExpression(anExpression);

        // then
        assertTrue(actual);
    }
