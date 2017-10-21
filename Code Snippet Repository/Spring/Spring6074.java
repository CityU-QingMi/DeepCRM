	@Test
	public void AccessingFactoryBean_spr9511() {
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new MyBeanResolver());
		Expression expr = new SpelExpressionParser().parseRaw("@foo");
		assertEquals("custard", expr.getValue(context));
		expr = new SpelExpressionParser().parseRaw("&foo");
		assertEquals("foo factory",expr.getValue(context));

		try {
			expr = new SpelExpressionParser().parseRaw("&@foo");
			fail("Illegal syntax, error expected");
		}
		catch (SpelParseException spe) {
			assertEquals(SpelMessage.INVALID_BEAN_REFERENCE,spe.getMessageCode());
			assertEquals(0,spe.getPosition());
		}

		try {
			expr = new SpelExpressionParser().parseRaw("@&foo");
			fail("Illegal syntax, error expected");
		}
		catch (SpelParseException spe) {
			assertEquals(SpelMessage.INVALID_BEAN_REFERENCE,spe.getMessageCode());
			assertEquals(0,spe.getPosition());
		}
	}
