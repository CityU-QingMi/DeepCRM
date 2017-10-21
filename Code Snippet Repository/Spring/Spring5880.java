	private void checkConstantList(String expressionText, boolean expectedToBeConstant) {
		SpelExpressionParser parser = new SpelExpressionParser();
		SpelExpression expression = (SpelExpression) parser.parseExpression(expressionText);
		SpelNode node = expression.getAST();
		assertTrue(node instanceof InlineList);
		InlineList inlineList = (InlineList) node;
		if (expectedToBeConstant) {
			assertTrue(inlineList.isConstant());
		}
		else {
			assertFalse(inlineList.isConstant());
		}
	}
