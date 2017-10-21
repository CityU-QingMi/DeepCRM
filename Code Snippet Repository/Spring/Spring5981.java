	@Test
	public void propertyReference() throws Exception {
		TestClass6 tc = new TestClass6();

		// non static field
		expression = parser.parseExpression("orange");
		assertCantCompile(expression);
		assertEquals("value1", expression.getValue(tc));
		assertCanCompile(expression);
		assertEquals("value1", expression.getValue(tc));

		// static field
		expression = parser.parseExpression("apple");
		assertCantCompile(expression);
		assertEquals("value2", expression.getValue(tc));
		assertCanCompile(expression);
		assertEquals("value2", expression.getValue(tc));

		// non static getter
		expression = parser.parseExpression("banana");
		assertCantCompile(expression);
		assertEquals("value3", expression.getValue(tc));
		assertCanCompile(expression);
		assertEquals("value3", expression.getValue(tc));

		// static getter
		expression = parser.parseExpression("plum");
		assertCantCompile(expression);
		assertEquals("value4", expression.getValue(tc));
		assertCanCompile(expression);
		assertEquals("value4", expression.getValue(tc));
	}
