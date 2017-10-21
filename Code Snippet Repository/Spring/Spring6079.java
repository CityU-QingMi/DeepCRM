	@Test
	public void SPR5847() throws Exception {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new TestProperties());
		String name = null;
		Expression expr = null;

		expr = new SpelExpressionParser().parseRaw("jdbcProperties['username']");
		name = expr.getValue(eContext, String.class);
		assertEquals("Dave", name);

		expr = new SpelExpressionParser().parseRaw("jdbcProperties[username]");
		name = expr.getValue(eContext, String.class);
		assertEquals("Dave", name);

		// MapAccessor required for this to work
		expr = new SpelExpressionParser().parseRaw("jdbcProperties.username");
		eContext.addPropertyAccessor(new MapAccessor());
		name = expr.getValue(eContext, String.class);
		assertEquals("Dave", name);

		// --- dotted property names

		// lookup foo on the root, then bar on that, then use that as the key into
		// jdbcProperties
		expr = new SpelExpressionParser().parseRaw("jdbcProperties[foo.bar]");
		eContext.addPropertyAccessor(new MapAccessor());
		name = expr.getValue(eContext, String.class);
		assertEquals("Dave2", name);

		// key is foo.bar
		expr = new SpelExpressionParser().parseRaw("jdbcProperties['foo.bar']");
		eContext.addPropertyAccessor(new MapAccessor());
		name = expr.getValue(eContext, String.class);
		assertEquals("Elephant", name);
	}
