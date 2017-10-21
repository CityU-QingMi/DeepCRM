	@Test
	public void testIsWritableForInvalidExpressions_SPR10610() {
		Expression e = null;
		StandardEvaluationContext lContext = TestScenarioCreator.getTestEvaluationContext();

		// PROPERTYORFIELDREFERENCE
		// Non existent field (or property):
		e = parser.parseExpression("arrayContainer.wibble");
		assertFalse("Should not be writable!",e.isWritable(lContext));

		e = parser.parseExpression("arrayContainer.wibble.foo");
		try {
			assertFalse("Should not be writable!",e.isWritable(lContext));
			fail("Should have had an error because wibble does not really exist");
		}
		catch (SpelEvaluationException see) {
//			org.springframework.expression.spel.SpelEvaluationException: EL1008E:(pos 15): Property or field 'wibble' cannot be found on object of type 'org.springframework.expression.spel.testresources.ArrayContainer' - maybe not public?
//					at org.springframework.expression.spel.ast.PropertyOrFieldReference.readProperty(PropertyOrFieldReference.java:225)
			// success!
		}

		// VARIABLE
		// the variable does not exist (but that is OK, we should be writable)
		e = parser.parseExpression("#madeup1");
		assertTrue("Should be writable!",e.isWritable(lContext));

		e = parser.parseExpression("#madeup2.bar"); // compound expression
		assertFalse("Should not be writable!",e.isWritable(lContext));

		// INDEXER
		// non existent indexer (wibble made up)
		e = parser.parseExpression("arrayContainer.wibble[99]");
		try {
			assertFalse("Should not be writable!",e.isWritable(lContext));
			fail("Should have had an error because wibble does not really exist");
		}
		catch (SpelEvaluationException see) {
			// success!
		}

		// non existent indexer (index via a string)
		e = parser.parseExpression("arrayContainer.ints['abc']");
		try {
			assertFalse("Should not be writable!",e.isWritable(lContext));
			fail("Should have had an error because wibble does not really exist");
		}
		catch (SpelEvaluationException see) {
			// success!
		}
	}
