	@Test
	public void setGenericPropertyContainingList() {
		List<Integer> property = new ArrayList<>();
		property.add(3);
		this.property = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("property");
		assertEquals("@org.springframework.expression.spel.IndexingTests$FieldAnnotation java.util.ArrayList<?>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("property[0]");
		assertEquals(3, expression.getValue(this));
		expression.setValue(this, "4");
		assertEquals("4", expression.getValue(this));
	}
