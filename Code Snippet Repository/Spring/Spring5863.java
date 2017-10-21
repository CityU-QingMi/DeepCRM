	@Test
	public void setPropertyContainingList() {
		List<Integer> property = new ArrayList<>();
		property.add(3);
		this.parameterizedList = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("parameterizedList");
		assertEquals("java.util.ArrayList<java.lang.Integer>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("parameterizedList[0]");
		assertEquals(3, expression.getValue(this));
		expression.setValue(this, "4");
		assertEquals(4, expression.getValue(this));
	}
