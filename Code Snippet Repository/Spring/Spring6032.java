	@Test
	public void testRelationalOperators() throws Exception {
		boolean result = parser.parseExpression("2 == 2").getValue(Boolean.class);
		assertTrue(result);
		// evaluates to false
		result = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
		assertFalse(result);

		// evaluates to true
		result = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
		assertTrue(result);
	}
