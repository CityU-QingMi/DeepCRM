	@SuppressWarnings("")
	@Test
	public void inlineList() throws Exception {
		expression = parser.parseExpression("'abcde'.substring({1,3,4}[0])");
		Object o = expression.getValue();
		assertEquals("bcde",o);
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals("bcde", o);

		expression = parser.parseExpression("{'abc','def'}");
		List<?> l = (List) expression.getValue();
		assertEquals("[abc, def]", l.toString());
		assertCanCompile(expression);
		l = (List) expression.getValue();
		assertEquals("[abc, def]", l.toString());

		expression = parser.parseExpression("{'abc','def'}[0]");
		o = expression.getValue();
		assertEquals("abc",o);
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals("abc", o);

		expression = parser.parseExpression("{'abcde','ijklm'}[0].substring({1,3,4}[0])");
		o = expression.getValue();
		assertEquals("bcde",o);
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals("bcde", o);

		expression = parser.parseExpression("{'abcde','ijklm'}[0].substring({1,3,4}[0],{1,3,4}[1])");
		o = expression.getValue();
		assertEquals("bc",o);
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals("bc", o);
	}
