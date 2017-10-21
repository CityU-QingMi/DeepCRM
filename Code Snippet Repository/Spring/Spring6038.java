	@Test
	public void indexingAsAPropertyAccess_SPR6968_4() {
		Goo g = Goo.instance;
		StandardEvaluationContext eContext = new StandardEvaluationContext(g);
		eContext.setVariable("bar", "wibble");
		Expression expr = null;
		expr = new SpelExpressionParser().parseRaw("instance[#bar]='world'");
		// will access the field 'wibble' and not use a getter
		expr.getValue(eContext, String.class);
		assertEquals("world", g.wibble);
		expr.getValue(eContext, String.class); // will be using the cached accessor this time
		assertEquals("world", g.wibble);
	}
