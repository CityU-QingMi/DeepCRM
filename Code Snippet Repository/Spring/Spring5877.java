	@Test
	public void setGenericPropertyContainingListAutogrow() {
		List<Integer> property = new ArrayList<>();
		this.property = property;
		SpelExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.ArrayList<?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("property[0]");
		try {
			expression.setValue(this, "4");
		}
		catch (EvaluationException ex) {
			assertTrue(ex.getMessage().startsWith("EL1053E"));
		}
	}
