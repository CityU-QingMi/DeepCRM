	private void checkNumber(String expression, Object value, Class<?> type) {
		try {
			SpelExpressionParser parser = new SpelExpressionParser();
			SpelExpression expr = parser.parseRaw(expression);
			Object exprVal = expr.getValue();
			assertEquals(value, exprVal);
			assertEquals(type, exprVal.getClass());
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
