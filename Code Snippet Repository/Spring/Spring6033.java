	@Test
	public void testOtherOperators() throws Exception {
		// evaluates to false
		boolean falseValue = parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);
		assertFalse(falseValue);

		// evaluates to true
		boolean trueValue = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		assertTrue(trueValue);

		//evaluates to false
		falseValue = parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		assertFalse(falseValue);
	}
