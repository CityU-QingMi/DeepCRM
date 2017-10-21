	@Test
	public void testLogicalOperators() throws Exception {

		StandardEvaluationContext societyContext = new StandardEvaluationContext();
		societyContext.setRootObject(new IEEE());

		// -- AND --

		// evaluates to false
		boolean falseValue = parser.parseExpression("true and false").getValue(Boolean.class);
		assertFalse(falseValue);
		// evaluates to true
		String expression =  "isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
		boolean trueValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);

		// -- OR --

		// evaluates to true
		trueValue = parser.parseExpression("true or false").getValue(Boolean.class);
		assertTrue(trueValue);

		// evaluates to true
		expression =  "isMember('Nikola Tesla') or isMember('Albert Einstien')";
		trueValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);
		assertTrue(trueValue);

		// -- NOT --

		// evaluates to false
		falseValue = parser.parseExpression("!true").getValue(Boolean.class);
		assertFalse(falseValue);


		// -- AND and NOT --
		expression =  "isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
		falseValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);
		assertFalse(falseValue);
	}
