	@Test
	public void indexingAsAPropertyAccess_SPR6968_3() {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new Goo());
		eContext.setVariable("bar", "wibble");
		String name = null;
		Expression expr = null;
		expr = new SpelExpressionParser().parseRaw("instance[#bar]");
		// will access the field 'wibble' and not use a getter
		name = expr.getValue(eContext, String.class);
		assertEquals("wobble", name);
		name = expr.getValue(eContext, String.class); // will be using the cached accessor this time
		assertEquals("wobble", name);
	}
