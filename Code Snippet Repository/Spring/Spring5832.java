	private void expectFail(ExpressionParser parser, EvaluationContext eContext, String expressionString, SpelMessage messageCode) {
		try {
			Expression e = parser.parseExpression(expressionString);
			 SpelUtilities.printAbstractSyntaxTree(System.out, e);
			e.getValue(eContext);
			fail();
		}
		catch (SpelEvaluationException see) {
			see.printStackTrace();
			assertEquals(messageCode,see.getMessageCode());
		}
	}
