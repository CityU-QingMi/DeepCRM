	@Test
	public void operatorNot() throws Exception {
		expression = parse("!true");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());

		expression = parse("!false");
		assertEquals(true, expression.getValue());
		assertCanCompile(expression);
		assertEquals(true, expression.getValue());

		boolean b = true;
		expression = parse("!#root");
		assertEquals(false, expression.getValue(b));
		assertCanCompile(expression);
		assertEquals(false, expression.getValue(b));

		b = false;
		expression = parse("!#root");
		assertEquals(true, expression.getValue(b));
		assertCanCompile(expression);
		assertEquals(true, expression.getValue(b));
	}
