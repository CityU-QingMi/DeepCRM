	@Test
	public void beanResolution() {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new XX());
		Expression expr = null;

		// no resolver registered == exception
		try {
			expr = new SpelExpressionParser().parseRaw("@foo");
			assertEquals("custard", expr.getValue(eContext, String.class));
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.NO_BEAN_RESOLVER_REGISTERED, see.getMessageCode());
			assertEquals("foo", see.getInserts()[0]);
		}

		eContext.setBeanResolver(new MyBeanResolver());

		// bean exists
		expr = new SpelExpressionParser().parseRaw("@foo");
		assertEquals("custard", expr.getValue(eContext, String.class));

		// bean does not exist
		expr = new SpelExpressionParser().parseRaw("@bar");
		assertEquals(null, expr.getValue(eContext, String.class));

		// bean name will cause AccessException
		expr = new SpelExpressionParser().parseRaw("@goo");
		try {
			assertEquals(null, expr.getValue(eContext, String.class));
		}
		catch (SpelEvaluationException see) {
			assertEquals(SpelMessage.EXCEPTION_DURING_BEAN_RESOLUTION, see.getMessageCode());
			assertEquals("goo", see.getInserts()[0]);
			assertTrue(see.getCause() instanceof AccessException);
			assertTrue(see.getCause().getMessage().startsWith("DONT"));
		}

		// bean exists
		expr = new SpelExpressionParser().parseRaw("@'foo.bar'");
		assertEquals("trouble", expr.getValue(eContext, String.class));

		// bean exists
		try {
			expr = new SpelExpressionParser().parseRaw("@378");
			assertEquals("trouble", expr.getValue(eContext, String.class));
		}
		catch (SpelParseException spe) {
			assertEquals(SpelMessage.INVALID_BEAN_REFERENCE, spe.getMessageCode());
		}
	}
