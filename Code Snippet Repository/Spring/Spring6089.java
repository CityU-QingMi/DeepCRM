	@Test
	public void testErrorCases() throws Exception {
		try {
			parser.parseExpression("hello ${'world'", DEFAULT_TEMPLATE_PARSER_CONTEXT);
			fail("Should have failed");
		}
		catch (ParseException pe) {
			assertEquals("No ending suffix '}' for expression starting at character 6: ${'world'", pe.getSimpleMessage());
			assertEquals("hello ${'world'", pe.getExpressionString());
		}
		try {
			parser.parseExpression("hello ${'wibble'${'world'}", DEFAULT_TEMPLATE_PARSER_CONTEXT);
			fail("Should have failed");
		}
		catch (ParseException pe) {
			assertEquals("No ending suffix '}' for expression starting at character 6: ${'wibble'${'world'}", pe.getSimpleMessage());
		}
		try {
			parser.parseExpression("hello ${} world", DEFAULT_TEMPLATE_PARSER_CONTEXT);
			fail("Should have failed");
		}
		catch (ParseException pe) {
			assertEquals("No expression defined within delimiter '${}' at character 6", pe.getSimpleMessage());
		}
	}
