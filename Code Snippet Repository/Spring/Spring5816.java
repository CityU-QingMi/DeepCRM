	@Test
	public void testAdvancedNumerics() throws Exception {
		int twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Integer.class);
		assertEquals(24, twentyFour);
		double one = parser.parseExpression("8.0 / 5e0 % 2").getValue(Double.class);
		assertEquals(1.6d, one, 0);
		int o = parser.parseExpression("8.0 / 5e0 % 2").getValue(Integer.class);
		assertEquals(1, o);
		int sixteen = parser.parseExpression("-2 ^ 4").getValue(Integer.class);
		assertEquals(16, sixteen);
		int minusFortyFive = parser.parseExpression("1+2-3*8^2/2/2").getValue(Integer.class);
		assertEquals(-45, minusFortyFive);
	}
