	@Test
	public void exceptions() {
		ExpressionException exprEx = new ExpressionException("test");
		assertEquals("test", exprEx.getSimpleMessage());
		assertEquals("test", exprEx.toDetailedString());
		assertEquals("test", exprEx.getMessage());

		exprEx = new ExpressionException("wibble", "test");
		assertEquals("test", exprEx.getSimpleMessage());
		assertEquals("Expression [wibble]: test", exprEx.toDetailedString());
		assertEquals("Expression [wibble]: test", exprEx.getMessage());

		exprEx = new ExpressionException("wibble", 3, "test");
		assertEquals("test", exprEx.getSimpleMessage());
		assertEquals("Expression [wibble] @3: test", exprEx.toDetailedString());
		assertEquals("Expression [wibble] @3: test", exprEx.getMessage());
	}
