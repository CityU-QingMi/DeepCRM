	private void checkNumberError(String expression, SpelMessage expectedMessage) {
		try {
			SpelExpressionParser parser = new SpelExpressionParser();
			parser.parseRaw(expression);
			fail();
		}
		catch (ParseException ex) {
			assertTrue(ex instanceof SpelParseException);
			SpelParseException spe = (SpelParseException) ex;
			assertEquals(expectedMessage, spe.getMessageCode());
		}
	}
