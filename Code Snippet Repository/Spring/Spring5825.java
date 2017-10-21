	@Test
	public void increment03() {
		Spr9751 helper = new Spr9751();
		StandardEvaluationContext ctx = new StandardEvaluationContext(helper);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression e = null;

		e = parser.parseExpression("m()++");
		try {
			e.getValue(ctx,Double.TYPE);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.OPERAND_NOT_INCREMENTABLE,see.getMessageCode());
		}

		e = parser.parseExpression("++m()");
		try {
			e.getValue(ctx,Double.TYPE);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.OPERAND_NOT_INCREMENTABLE,see.getMessageCode());
		}
	}
