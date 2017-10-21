	@Test
	public void increment04() {
		Integer i = 42;
		StandardEvaluationContext ctx = new StandardEvaluationContext(i);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		try {
			Expression e =  parser.parseExpression("++1");
			e.getValue(ctx,Integer.class);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.NOT_ASSIGNABLE,see.getMessageCode());
		}
		try {
			Expression e =  parser.parseExpression("1++");
			e.getValue(ctx,Integer.class);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.NOT_ASSIGNABLE,see.getMessageCode());
		}
	}
