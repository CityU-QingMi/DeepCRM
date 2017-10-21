	private void checkError(String pattern, int expectedPos, PatternMessage expectedMessage, String... expectedInserts) {
		try {
			pathPattern = parse(pattern);
			fail("Expected to fail");
		}
		catch (PatternParseException ppe) {
			assertEquals(ppe.toDetailedString(), expectedPos, ppe.getPosition());
			assertEquals(ppe.toDetailedString(), expectedMessage, ppe.getMessageType());
			if (expectedInserts.length != 0) {
				assertEquals(ppe.getInserts().length, expectedInserts.length);
				for (int i = 0; i < expectedInserts.length; i++) {
					assertEquals("Insert at position " + i + " is wrong", expectedInserts[i], ppe.getInserts()[i]);
				}
			}
		}
	}
