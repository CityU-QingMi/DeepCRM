	@Nullable
	private SpelNodeImpl eatLogicalOrExpression() {
		SpelNodeImpl expr = eatLogicalAndExpression();
		while (peekIdentifierToken("or") || peekToken(TokenKind.SYMBOLIC_OR)) {
			Token t = takeToken();  //consume OR
			SpelNodeImpl rhExpr = eatLogicalAndExpression();
			checkOperands(t, expr, rhExpr);
			expr = new OpOr(toPos(t), expr, rhExpr);
		}
		return expr;
	}
