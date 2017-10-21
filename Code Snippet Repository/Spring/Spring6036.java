	@Test
	public void dollarPrefixedIdentifier_SPR7100() {
		Holder h = new Holder();
		StandardEvaluationContext eContext = new StandardEvaluationContext(h);
		eContext.addPropertyAccessor(new MapAccessor());
		h.map.put("$foo", "wibble");
		h.map.put("foo$bar", "wobble");
		h.map.put("foobar$$", "wabble");
		h.map.put("$", "wubble");
		h.map.put("$$", "webble");
		h.map.put("$_$", "tribble");
		String name = null;
		Expression expr = null;

		expr = new SpelExpressionParser().parseRaw("map.$foo");
		name = expr.getValue(eContext, String.class);
		assertEquals("wibble", name);

		expr = new SpelExpressionParser().parseRaw("map.foo$bar");
		name = expr.getValue(eContext, String.class);
		assertEquals("wobble", name);

		expr = new SpelExpressionParser().parseRaw("map.foobar$$");
		name = expr.getValue(eContext, String.class);
		assertEquals("wabble", name);

		expr = new SpelExpressionParser().parseRaw("map.$");
		name = expr.getValue(eContext, String.class);
		assertEquals("wubble", name);

		expr = new SpelExpressionParser().parseRaw("map.$$");
		name = expr.getValue(eContext, String.class);
		assertEquals("webble", name);

		expr = new SpelExpressionParser().parseRaw("map.$_$");
		name = expr.getValue(eContext, String.class);
		assertEquals("tribble", name);
	}
