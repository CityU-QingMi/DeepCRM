	private boolean maybeEatParenExpression() {
		if (peekToken(TokenKind.LPAREN)) {
			nextToken();
			SpelNodeImpl expr = eatExpression();
			Assert.state(expr != null, "No node");
			eatToken(TokenKind.RPAREN);
			push(expr);
			return true;
		}
		else {
			return false;
		}
	}
