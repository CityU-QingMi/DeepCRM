	@Test
	public void propertyAccessorOrder_8211() {
		ExpressionParser expressionParser = new SpelExpressionParser();
		StandardEvaluationContext evaluationContext = new StandardEvaluationContext(new ContextObject());

		evaluationContext.addPropertyAccessor(new TestPropertyAccessor("firstContext"));
		evaluationContext.addPropertyAccessor(new TestPropertyAccessor("secondContext"));
		evaluationContext.addPropertyAccessor(new TestPropertyAccessor("thirdContext"));
		evaluationContext.addPropertyAccessor(new TestPropertyAccessor("fourthContext"));

		assertEquals("first", expressionParser.parseExpression("shouldBeFirst").getValue(evaluationContext));
		assertEquals("second", expressionParser.parseExpression("shouldBeSecond").getValue(evaluationContext));
		assertEquals("third", expressionParser.parseExpression("shouldBeThird").getValue(evaluationContext));
		assertEquals("fourth", expressionParser.parseExpression("shouldBeFourth").getValue(evaluationContext));
	}
