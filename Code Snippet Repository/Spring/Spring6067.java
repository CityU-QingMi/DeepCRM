	@Test
	public void SPR11142() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		Spr11142 rootObject = new Spr11142();
		Expression expression = parser.parseExpression("something");
		thrown.expect(SpelEvaluationException.class);
		thrown.expectMessage("'something' cannot be found");
		expression.getValue(context, rootObject);
	}
