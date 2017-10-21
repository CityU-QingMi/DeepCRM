	private boolean maybeEatProjection(boolean nullSafeNavigation) {
		Token t = peekToken();
		if (!peekToken(TokenKind.PROJECT, true)) {
			return false;
		}
		Assert.state(t != null, "No token");
		SpelNodeImpl expr = eatExpression();
		Assert.state(expr != null, "No node");
		eatToken(TokenKind.RSQUARE);
		this.constructedNodes.push(new Projection(nullSafeNavigation, toPos(t), expr));
		return true;
	}
