	@Test
	public void elvis_SPR7209_2() {
		Expression expr = null;
		// Have empty string treated as null for elvis
		expr = new SpelExpressionParser().parseRaw("?:'default'");
		assertEquals("default", expr.getValue());
		expr = new SpelExpressionParser().parseRaw("\"\"?:'default'");
		assertEquals("default", expr.getValue());
		expr = new SpelExpressionParser().parseRaw("''?:'default'");
		assertEquals("default", expr.getValue());
	}
