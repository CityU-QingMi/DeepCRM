	public void parseCheck(String expression, String expectedStringFormOfAST) {
		try {
			SpelExpression e = parser.parseRaw(expression);
			if (e != null && !e.toStringAST().equals(expectedStringFormOfAST)) {
				SpelUtilities.printAbstractSyntaxTree(System.err, e);
			}
			if (e == null) {
				fail("Parsed exception was null");
			}
			assertEquals("String form of AST does not match expected output", expectedStringFormOfAST, e.toStringAST());
		}
		catch (ParseException ee) {
			ee.printStackTrace();
			fail("Unexpected Exception: " + ee.getMessage());
		}
	}
