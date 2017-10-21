	@Test
	public void testPropertyNavigation() throws Exception {
		ExpressionParser parser = new SpelExpressionParser();

		// Inventions Array
		StandardEvaluationContext teslaContext = TestScenarioCreator.getTestEvaluationContext();
//		teslaContext.setRootObject(tesla);

		// evaluates to "Induction motor"
		String invention = parser.parseExpression("inventions[3]").getValue(teslaContext, String.class);
		assertEquals("Induction motor",invention);

		// Members List
		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		IEEE ieee = new IEEE();
		ieee.Members[0]= tesla;
		societyContext.setRootObject(ieee);

		// evaluates to "Nikola Tesla"
		String name = parser.parseExpression("Members[0].Name").getValue(societyContext, String.class);
		assertEquals("Nikola Tesla",name);

		// List and Array navigation
		// evaluates to "Wireless communication"
		invention = parser.parseExpression("Members[0].Inventions[6]").getValue(societyContext, String.class);
		assertEquals("Wireless communication",invention);
	}
