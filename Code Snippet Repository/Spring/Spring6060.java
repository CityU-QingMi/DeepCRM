	@Test
	public void array() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		Expression expression = null;
		Object result = null;

		expression = parser.parseExpression("new java.lang.Long[0].class");
		result = expression.getValue(context, "");
		assertEquals("Equal assertion failed: ", "class [Ljava.lang.Long;", result.toString());

		expression = parser.parseExpression("T(java.lang.Long[])");
		result = expression.getValue(context, "");
		assertEquals("Equal assertion failed: ", "class [Ljava.lang.Long;", result.toString());

		expression = parser.parseExpression("T(java.lang.String[][][])");
		result = expression.getValue(context, "");
		assertEquals("Equal assertion failed: ", "class [[[Ljava.lang.String;", result.toString());
		assertEquals("T(java.lang.String[][][])", ((SpelExpression) expression).toStringAST());

		expression = parser.parseExpression("new int[0].class");
		result = expression.getValue(context, "");
		assertEquals("Equal assertion failed: ", "class [I", result.toString());

		expression = parser.parseExpression("T(int[][])");
		result = expression.getValue(context, "");
		assertEquals("class [[I", result.toString());
	}
