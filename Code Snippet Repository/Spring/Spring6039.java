	@Test
	public void indexingAsAPropertyAccess_SPR6968_5() {
		Goo g = Goo.instance;
		StandardEvaluationContext eContext = new StandardEvaluationContext(g);
		Expression expr = null;
		expr = new SpelExpressionParser().parseRaw("instance[bar]='world'");
		expr.getValue(eContext, String.class);
		assertEquals("world", g.value);
		expr.getValue(eContext, String.class); // will be using the cached accessor this time
		assertEquals("world", g.value);
	}
