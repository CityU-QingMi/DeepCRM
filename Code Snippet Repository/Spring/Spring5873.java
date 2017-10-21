	@Test
	public void setPropertyContainingMap() {
		Map<Integer, Integer> property = new HashMap<>();
		property.put(9, 3);
		this.parameterizedMap = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("parameterizedMap");
		assertEquals("java.util.HashMap<java.lang.Integer, java.lang.Integer>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("parameterizedMap['9']");
		assertEquals(3, expression.getValue(this));
		expression.setValue(this, "37");
		assertEquals(37, expression.getValue(this));
	}
