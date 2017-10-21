	@Test
	public void indexIntoGenericPropertyContainingGrowingList2() {
		List<String> property2 = new ArrayList<>();
		this.property2 = property2;
		SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
		SpelExpressionParser parser = new SpelExpressionParser(configuration);
		Expression expression = parser.parseExpression("property2");
		assertEquals("java.util.ArrayList<?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property2, expression.getValue(this));
		expression = parser.parseExpression("property2[0]");
		try {
			assertEquals("bar", expression.getValue(this));
		}
		catch (EvaluationException ex) {
			assertTrue(ex.getMessage().startsWith("EL1053E"));
		}
	}
