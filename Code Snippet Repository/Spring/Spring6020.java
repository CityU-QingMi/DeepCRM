	@Test
	public void testNumericalOperators() throws Exception {
		// Addition
		int two = parser.parseExpression("1 + 1").getValue(Integer.class); // 2
		assertEquals(2,two);

		String testString = parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class); // 'test string'
		assertEquals("test string",testString);

		// Subtraction
		int four =  parser.parseExpression("1 - -3").getValue(Integer.class); // 4
		assertEquals(4,four);

		double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class); // -9000
		assertEquals(-9000.0d, d, 0);

		// Multiplication
		int six =  parser.parseExpression("-2 * -3").getValue(Integer.class); // 6
		assertEquals(6,six);

		double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0
		assertEquals(24.0d, twentyFour, 0);

		// Division
		int minusTwo =  parser.parseExpression("6 / -3").getValue(Integer.class); // -2
		assertEquals(-2,minusTwo);

		double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class); // 1.0
		assertEquals(1.0d, one, 0);

		// Modulus
		int three =  parser.parseExpression("7 % 4").getValue(Integer.class); // 3
		assertEquals(3,three);

		int oneInt = parser.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1
		assertEquals(1,oneInt);

		// Operator precedence
		int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class); // -21
		assertEquals(-21,minusTwentyOne);
	}
