	@Test
	public void plusNeedingCheckcast_SPR12426() {
		expression = parser.parseExpression("object + ' world'");
		Object v = expression.getValue(new FooObject());
		assertEquals("hello world", v);
		assertCanCompile(expression);
		assertEquals("hello world", v);

		expression = parser.parseExpression("object + ' world'");
		v = expression.getValue(new FooString());
		assertEquals("hello world", v);
		assertCanCompile(expression);
		assertEquals("hello world", v);
	}
