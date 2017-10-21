	public void testClashingWithSuffixes() throws Exception {
		// Just wanting to use the prefix or suffix within the template:
		Expression ex = parser.parseExpression("hello ${3+4} world",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		String s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello 7 world", s);

		ex = parser.parseExpression("hello ${3+4} wo${'${'}rld",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello 7 wo${rld", s);

		ex = parser.parseExpression("hello ${3+4} wo}rld",DEFAULT_TEMPLATE_PARSER_CONTEXT);
		s = ex.getValue(TestScenarioCreator.getTestEvaluationContext(),String.class);
		assertEquals("hello 7 wo}rld", s);
	}
