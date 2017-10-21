	protected void parseAndCheckError(String expression, SpelMessage expectedMessage, Object... otherProperties) {
		try {
			Expression expr = parser.parseExpression(expression);
			SpelUtilities.printAbstractSyntaxTree(System.out, expr);
			fail("Parsing should have failed!");
		}
		catch (ParseException pe) {
			SpelParseException ex = (SpelParseException)pe;
			if (ex.getMessageCode() != expectedMessage) {
				assertEquals("Failed to get expected message", expectedMessage, ex.getMessageCode());
			}
			if (otherProperties != null && otherProperties.length != 0) {
				// first one is expected position of the error within the string
				int pos = ((Integer) otherProperties[0]).intValue();
				assertEquals("Did not get correct position reported in error ", pos, ex.getPosition());
				if (otherProperties.length > 1) {
					// Check inserts match
					Object[] inserts = ex.getInserts();
					if (inserts == null) {
						inserts = new Object[0];
					}
					if (inserts.length < otherProperties.length - 1) {
						fail("Cannot check " + (otherProperties.length - 1) +
								" properties of the exception, it only has " + inserts.length + " inserts");
					}
					for (int i = 1; i < otherProperties.length; i++) {
						if (!inserts[i - 1].equals(otherProperties[i])) {
							fail("Insert does not match, expected '" + otherProperties[i] +
									"' but insert value was '" + inserts[i - 1] + "'");
						}
					}
				}
			}
		}
	}
