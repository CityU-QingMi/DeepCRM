	@Test
	public void testSimpleOperations() throws Exception {
		// no built in support for this:
		evaluateAndCheckError("'abc'-true",SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);

		StandardEvaluationContext eContext = TestScenarioCreator.getTestEvaluationContext();
		eContext.setOperatorOverloader(new StringAndBooleanAddition());

		SpelExpression expr = (SpelExpression)parser.parseExpression("'abc'+true");
		assertEquals("abctrue",expr.getValue(eContext));

		expr = (SpelExpression)parser.parseExpression("'abc'-true");
		assertEquals("abc",expr.getValue(eContext));

		expr = (SpelExpression)parser.parseExpression("'abc'+null");
		assertEquals("abcnull",expr.getValue(eContext));
	}
