	@Test
	public void compilerWithGenerics_12040_3() {
		expression = parser.parseExpression("payload >= 2");
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(4), Boolean.TYPE));
		assertCanCompile(expression);
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));

		expression = parser.parseExpression("2 >= payload");
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(5), Boolean.TYPE));
		assertCanCompile(expression);
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));

		expression = parser.parseExpression("payload > 2");
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(4), Boolean.TYPE));
		assertCanCompile(expression);
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));

		expression = parser.parseExpression("2 > payload");
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(5), Boolean.TYPE));
		assertCanCompile(expression);
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));

		expression = parser.parseExpression("payload <=2");
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));
		assertCanCompile(expression);
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(6), Boolean.TYPE));

		expression = parser.parseExpression("2 <= payload");
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));
		assertCanCompile(expression);
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(6), Boolean.TYPE));

		expression = parser.parseExpression("payload < 2");
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));
		assertCanCompile(expression);
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(6), Boolean.TYPE));

		expression = parser.parseExpression("2 < payload");
		assertFalse(expression.getValue(new GenericMessageTestHelper2<>(1), Boolean.TYPE));
		assertCanCompile(expression);
		assertTrue(expression.getValue(new GenericMessageTestHelper2<>(6), Boolean.TYPE));
	}
