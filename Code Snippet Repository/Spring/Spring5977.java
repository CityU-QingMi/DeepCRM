	@Test
	public void methodReferenceReflectiveMethodSelectionWithVarargs() throws Exception {
		TestClass10 tc = new TestClass10();

		// Should call the non varargs version of concat
		// (which causes the '::' prefix in test output)
		expression = parser.parseExpression("concat('test')");
		assertCantCompile(expression);
		expression.getValue(tc);
		assertEquals("::test", tc.s);
		assertCanCompile(expression);
		tc.reset();
		expression.getValue(tc);
		assertEquals("::test", tc.s);
		tc.reset();

		// This will call the varargs concat with an empty array
		expression = parser.parseExpression("concat()");
		assertCantCompile(expression);
		expression.getValue(tc);
		assertEquals("", tc.s);
		assertCanCompile(expression);
		tc.reset();
		expression.getValue(tc);
		assertEquals("", tc.s);
		tc.reset();

		// Should call the non varargs version of concat
		// (which causes the '::' prefix in test output)
		expression = parser.parseExpression("concat2('test')");
		assertCantCompile(expression);
		expression.getValue(tc);
		assertEquals("::test", tc.s);
		assertCanCompile(expression);
		tc.reset();
		expression.getValue(tc);
		assertEquals("::test", tc.s);
		tc.reset();

		// This will call the varargs concat with an empty array
		expression = parser.parseExpression("concat2()");
		assertCantCompile(expression);
		expression.getValue(tc);
		assertEquals("", tc.s);
		assertCanCompile(expression);
		tc.reset();
		expression.getValue(tc);
		assertEquals("", tc.s);
		tc.reset();
	}
