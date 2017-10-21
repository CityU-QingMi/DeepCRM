	@Test
	public void indexIntoPropertyContainingListOfList() {
		List<List<Integer>> property = new ArrayList<>();
		property.add(Arrays.asList(3));
		this.parameterizedListOfList = property;
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("parameterizedListOfList[0]");
		assertEquals("java.util.Arrays$ArrayList<java.lang.Integer>", expression.getValueTypeDescriptor(this).toString());
		assertEquals(property.get(0), expression.getValue(this));
		expression = parser.parseExpression("parameterizedListOfList[0][0]");
		assertEquals(3, expression.getValue(this));
	}
