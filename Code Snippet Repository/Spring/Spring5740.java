	private boolean maybeEatSelection(boolean nullSafeNavigation) {
		Token t = peekToken();
		if (!peekSelectToken()) {
			return false;
		}
		Assert.state(t != null, "No token");
		nextToken();
		SpelNodeImpl expr = eatExpression();
		if (expr == null) {
			throw internalException(toPos(t), SpelMessage.MISSING_SELECTION_EXPRESSION);
		}
		eatToken(TokenKind.RSQUARE);
		if (t.kind == TokenKind.SELECT_FIRST) {
			this.constructedNodes.push(new Selection(nullSafeNavigation, Selection.FIRST, toPos(t), expr));
		}
		else if (t.kind == TokenKind.SELECT_LAST) {
			this.constructedNodes.push(new Selection(nullSafeNavigation, Selection.LAST, toPos(t), expr));
		}
		else {
			this.constructedNodes.push(new Selection(nullSafeNavigation, Selection.ALL, toPos(t), expr));
		}
		return true;
	}
