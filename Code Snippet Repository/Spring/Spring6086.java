	@Test
	public void testParsingSimpleTemplateExpression04() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("${'hello'} world", DEFAULT_TEMPLATE_PARSER_CONTEXT);
		Object o = expr.getValue();
		assertEquals("hello world", o.toString());

		expr = parser.parseExpression("", DEFAULT_TEMPLATE_PARSER_CONTEXT);
		o = expr.getValue();
		assertEquals("", o.toString());

		expr = parser.parseExpression("abc", DEFAULT_TEMPLATE_PARSER_CONTEXT);
		o = expr.getValue();
		assertEquals("abc", o.toString());

		expr = parser.parseExpression("abc", DEFAULT_TEMPLATE_PARSER_CONTEXT);
		o = expr.getValue((Object)null);
		assertEquals("abc", o.toString());
	}
