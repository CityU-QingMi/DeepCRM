	@Test
	public void indexIntoGenericPropertyContainingList() {
		List<String> property = new ArrayList<>();
		property.add("bar");
		this.property = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.ArrayList<?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("property[0]");
		assertEquals("bar", expression.getValue(this));
	}
