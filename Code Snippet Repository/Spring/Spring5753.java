	@Nullable
	private SpelNodeImpl eatSumExpression() {
		SpelNodeImpl expr = eatProductExpression();
		while (peekToken(TokenKind.PLUS, TokenKind.MINUS, TokenKind.INC)) {
			Token t = takeToken();  //consume PLUS or MINUS or INC
			SpelNodeImpl rhExpr = eatProductExpression();
			checkRightOperand(t, rhExpr);
			if (t.kind == TokenKind.PLUS) {
				expr = new OpPlus(toPos(t), expr, rhExpr);
			}
			else if (t.kind == TokenKind.MINUS) {
				expr = new OpMinus(toPos(t), expr, rhExpr);
			}
		}
		return expr;
	}
