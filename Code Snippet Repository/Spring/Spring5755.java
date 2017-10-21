	@Nullable
	private SpelNodeImpl eatPowerIncDecExpression() {
		SpelNodeImpl expr = eatUnaryExpression();
		if (peekToken(TokenKind.POWER)) {
			Token t = takeToken();  //consume POWER
			SpelNodeImpl rhExpr = eatUnaryExpression();
			checkRightOperand(t, rhExpr);
			return new OperatorPower(toPos(t), expr, rhExpr);
		}
		if (expr != null && peekToken(TokenKind.INC, TokenKind.DEC)) {
			Token t = takeToken();  //consume INC/DEC
			if (t.getKind() == TokenKind.INC) {
				return new OpInc(toPos(t), true, expr);
			}
			return new OpDec(toPos(t), true, expr);
		}
		return expr;
	}
