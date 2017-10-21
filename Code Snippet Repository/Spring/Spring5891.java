	private void checkConstantMap(String expressionText, boolean expectedToBeConstant) {
		SpelExpressionParser parser = new SpelExpressionParser();
		SpelExpression expression = (SpelExpression) parser.parseExpression(expressionText);
		SpelNode node = expression.getAST();
		assertTrue(node instanceof InlineMap);
		InlineMap inlineMap = (InlineMap) node;
		if (expectedToBeConstant) {
			assertTrue(inlineMap.isConstant());
		}
		else {
			assertFalse(inlineMap.isConstant());
		}
	}
