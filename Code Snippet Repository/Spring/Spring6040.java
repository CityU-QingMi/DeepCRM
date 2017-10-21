	private void checkTemplateParsingError(String expression, ParserContext context, String expectedMessage) throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		try {
			parser.parseExpression(expression, context);
			fail("Should have failed with message: " + expectedMessage);
		}
		catch (Exception ex) {
			String message = ex.getMessage();
			if (ex instanceof ExpressionException) {
				message = ((ExpressionException) ex).getSimpleMessage();
			}
			if (!message.equals(expectedMessage)) {
				ex.printStackTrace();
			}
			assertThat(expectedMessage, equalTo(message));
		}
	}
