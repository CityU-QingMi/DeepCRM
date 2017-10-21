	private boolean maybeEatIndexer() {
		Token t = peekToken();
		if (!peekToken(TokenKind.LSQUARE, true)) {
			return false;
		}
		Assert.state(t != null, "No token");
		SpelNodeImpl expr = eatExpression();
		Assert.state(expr != null, "No node");
		eatToken(TokenKind.RSQUARE);
		this.constructedNodes.push(new Indexer(toPos(t), expr));
		return true;
	}
