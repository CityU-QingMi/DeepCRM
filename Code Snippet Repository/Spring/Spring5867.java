	@Test
	public void indexIntoGenericPropertyContainingArray() {
		String[] property = new String[] { "bar" };
		this.property = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.lang.String[]", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("property[0]");
		assertEquals("bar", expression.getValue(this));
	}
