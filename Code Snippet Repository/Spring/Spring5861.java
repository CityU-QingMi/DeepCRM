	@Test
	public void indexIntoGenericPropertyContainingMap() {
		Map<String, String> property = new HashMap<>();
		property.put("foo", "bar");
		this.property = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.HashMap<?, ?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		assertEquals(property, expression.getValue(this, Map.class));
		expression = parser.parseExpression("property['foo']");
		assertEquals("bar", expression.getValue(this));
	}
