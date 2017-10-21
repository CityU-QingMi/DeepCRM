	@Test
	public void indexIntoGenericPropertyContainingNullList() {
		SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
		SpelExpressionParser parser = new SpelExpressionParser(configuration);
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.lang.Object", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("property[0]");
		try {
			assertEquals("bar", expression.getValue(this));
		}
		catch (EvaluationException ex) {
			assertTrue(ex.getMessage().startsWith("EL1027E"));
		}
	}
