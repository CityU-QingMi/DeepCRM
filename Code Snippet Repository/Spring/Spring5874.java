	@Test
	public void setPropertyContainingMapAutoGrow() {
		SpelExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, false));
		Expression expression = parser.parseExpression("parameterizedMap");
		assertEquals("java.util.Map<java.lang.Integer, java.lang.Integer>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property, expression.getValue(this));
		expression = parser.parseExpression("parameterizedMap['9']");
		assertEquals(null, expression.getValue(this));
		expression.setValue(this, "37");
		assertEquals(37, expression.getValue(this));
	}
