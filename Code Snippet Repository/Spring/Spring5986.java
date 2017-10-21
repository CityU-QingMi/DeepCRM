	@Test
	public void compilerWithGenerics_12040_2() {
		expression = parser.parseExpression("payload/2");
		assertEquals(2, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(3, expression.getValue(new GenericMessageTestHelper2<>(6)));

		expression = parser.parseExpression("9/payload");
		assertEquals(1, expression.getValue(new GenericMessageTestHelper2<>(9)));
		assertCanCompile(expression);
		assertEquals(3, expression.getValue(new GenericMessageTestHelper2<>(3)));

		expression = parser.parseExpression("payload+2");
		assertEquals(6, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(8, expression.getValue(new GenericMessageTestHelper2<>(6)));

		expression = parser.parseExpression("100+payload");
		assertEquals(104, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(110, expression.getValue(new GenericMessageTestHelper2<>(10)));

		expression = parser.parseExpression("payload-2");
		assertEquals(2, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(4, expression.getValue(new GenericMessageTestHelper2<>(6)));

		expression = parser.parseExpression("100-payload");
		assertEquals(96, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(90, expression.getValue(new GenericMessageTestHelper2<>(10)));

		expression = parser.parseExpression("payload*2");
		assertEquals(8, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(12, expression.getValue(new GenericMessageTestHelper2<>(6)));

		expression = parser.parseExpression("100*payload");
		assertEquals(400, expression.getValue(new GenericMessageTestHelper2<>(4)));
		assertCanCompile(expression);
		assertEquals(1000, expression.getValue(new GenericMessageTestHelper2<>(10)));
	}
