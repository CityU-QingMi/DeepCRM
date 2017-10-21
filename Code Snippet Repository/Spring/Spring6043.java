	@Test
	public void elvis_SPR7209_1() {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new XX());
		Expression expr = null;

		// Different parts of elvis expression are null
		expr = new SpelExpressionParser().parseRaw("(?:'default')");
		assertEquals("default", expr.getValue());
		expr = new SpelExpressionParser().parseRaw("?:'default'");
		assertEquals("default", expr.getValue());
		expr = new SpelExpressionParser().parseRaw("?:");
		assertEquals(null, expr.getValue());

		// Different parts of ternary expression are null
		try {
			expr = new SpelExpressionParser().parseRaw("(?'abc':'default')");
			expr.getValue(eContext);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.TYPE_CONVERSION_ERROR, see.getMessageCode());
		}
		expr = new SpelExpressionParser().parseRaw("(false?'abc':null)");
		assertEquals(null, expr.getValue());

		// Assignment
		try {
			expr = new SpelExpressionParser().parseRaw("(='default')");
			expr.getValue(eContext);
			fail();
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.SETVALUE_NOT_SUPPORTED, see.getMessageCode());
		}
	}
