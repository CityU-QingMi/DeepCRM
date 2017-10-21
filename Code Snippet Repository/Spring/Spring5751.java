	@Nullable
	private SpelNodeImpl eatLogicalAndExpression() {
		SpelNodeImpl expr = eatRelationalExpression();
		while (peekIdentifierToken("and") || peekToken(TokenKind.SYMBOLIC_AND)) {
			Token t = takeToken();  // consume 'AND'
			SpelNodeImpl rhExpr = eatRelationalExpression();
			checkOperands(t, expr, rhExpr);
			expr = new OpAnd(toPos(t), expr, rhExpr);
		}
		return expr;
	}
