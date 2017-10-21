	@Nullable
	private SpelNodeImpl eatUnaryExpression() {
		if (peekToken(TokenKind.PLUS, TokenKind.MINUS, TokenKind.NOT)) {
			Token t = takeToken();
			SpelNodeImpl expr = eatUnaryExpression();
			Assert.state(expr != null, "No node");
			if (t.kind == TokenKind.NOT) {
				return new OperatorNot(toPos(t), expr);
			}
			if (t.kind == TokenKind.PLUS) {
				return new OpPlus(toPos(t), expr);
			}
			Assert.isTrue(t.kind == TokenKind.MINUS, "Minus token expected");
			return new OpMinus(toPos(t), expr);
		}
		if (peekToken(TokenKind.INC, TokenKind.DEC)) {
			Token t = takeToken();
			SpelNodeImpl expr = eatUnaryExpression();
			if (t.getKind() == TokenKind.INC) {
				return new OpInc(toPos(t), false, expr);
			}
			return new OpDec(toPos(t), false, expr);
		}
		return eatPrimaryExpression();
	}
