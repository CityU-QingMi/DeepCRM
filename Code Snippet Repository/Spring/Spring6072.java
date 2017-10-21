	@Test
	public void SPR12808() {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("T(org.springframework.expression.spel.SpelReproTests.DistanceEnforcer).from(#no)");
		StandardEvaluationContext sec = new StandardEvaluationContext();
		sec.setVariable("no", new Integer(1));
		assertTrue(expression.getValue(sec).toString().startsWith("Integer"));
		sec = new StandardEvaluationContext();
		sec.setVariable("no", new Float(1.0));
		assertTrue(expression.getValue(sec).toString().startsWith("Number"));
		sec = new StandardEvaluationContext();
		sec.setVariable("no", "1.0");
		assertTrue(expression.getValue(sec).toString().startsWith("Object"));
	}
