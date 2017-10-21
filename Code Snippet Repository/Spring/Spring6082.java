	@Test
	public void indexingAsAPropertyAccess_SPR6968_1() {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new Goo());
		String name = null;
		Expression expr = null;
		expr = new SpelExpressionParser().parseRaw("instance[bar]");
		name = expr.getValue(eContext, String.class);
		assertEquals("hello", name);
		name = expr.getValue(eContext, String.class); // will be using the cached accessor this time
		assertEquals("hello", name);
	}
