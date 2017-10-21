	@Test
	public void testTemplateParserContext() {
		TemplateParserContext tpc = new TemplateParserContext("abc","def");
		assertEquals("abc", tpc.getExpressionPrefix());
		assertEquals("def", tpc.getExpressionSuffix());
		assertTrue(tpc.isTemplate());

		tpc = new TemplateParserContext();
		assertEquals("#{", tpc.getExpressionPrefix());
		assertEquals("}", tpc.getExpressionSuffix());
		assertTrue(tpc.isTemplate());

		ParserContext pc = ParserContext.TEMPLATE_EXPRESSION;
		assertEquals("#{", pc.getExpressionPrefix());
		assertEquals("}", pc.getExpressionSuffix());
		assertTrue(pc.isTemplate());
	}
