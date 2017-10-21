	@Test
	public void increment01root() {
		Integer i = 42;
		StandardEvaluationContext ctx = new StandardEvaluationContext(i);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression e =  parser.parseExpression("#this++");
		assertEquals(42,i.intValue());
		try {
			e.getValue(ctx,Integer.class);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.NOT_ASSIGNABLE,see.getMessageCode());
		}
	}
