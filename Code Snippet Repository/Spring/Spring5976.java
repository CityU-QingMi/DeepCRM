	@Test
	public void constructorReference() throws Exception {
		// simple ctor
		expression = parser.parseExpression("new String('123')");
		assertEquals("123", expression.getValue());
		assertCanCompile(expression);
		assertEquals("123", expression.getValue());

		String testclass8 = "org.springframework.expression.spel.SpelCompilationCoverageTests$TestClass8";
		// multi arg ctor that includes primitives
		expression = parser.parseExpression("new " + testclass8 + "(42,'123',4.0d,true)");
		assertEquals(testclass8, expression.getValue().getClass().getName());
		assertCanCompile(expression);
		Object o = expression.getValue();
		assertEquals(testclass8,o.getClass().getName());
		TestClass8 tc8 = (TestClass8) o;
		assertEquals(42, tc8.i);
		assertEquals("123", tc8.s);
		assertEquals(4.0d, tc8.d, 0.5d);
		assertEquals(true, tc8.z);

		// no-arg ctor
		expression = parser.parseExpression("new " + testclass8 + "()");
		assertEquals(testclass8, expression.getValue().getClass().getName());
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals(testclass8,o.getClass().getName());

		// pass primitive to reference type ctor
		expression = parser.parseExpression("new " + testclass8 + "(42)");
		assertEquals(testclass8, expression.getValue().getClass().getName());
		assertCanCompile(expression);
		o = expression.getValue();
		assertEquals(testclass8,o.getClass().getName());
		tc8 = (TestClass8) o;
		assertEquals(42, tc8.i);

		// private class, can't compile it
		String testclass9 = "org.springframework.expression.spel.SpelCompilationCoverageTests$TestClass9";
		expression = parser.parseExpression("new " + testclass9 + "(42)");
		assertEquals(testclass9, expression.getValue().getClass().getName());
		assertCantCompile(expression);
	}
