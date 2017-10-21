	@Nullable
	private SpelNodeImpl eatProductExpression() {
		SpelNodeImpl expr = eatPowerIncDecExpression();
		while (peekToken(TokenKind.STAR, TokenKind.DIV, TokenKind.MOD)) {
			Token t = takeToken();  // consume STAR/DIV/MOD
			SpelNodeImpl rhExpr = eatPowerIncDecExpression();
			checkOperands(t, expr, rhExpr);
			if (t.kind == TokenKind.STAR) {
				expr = new OpMultiply(toPos(t), expr, rhExpr);
			}
			else if (t.kind == TokenKind.DIV) {
				expr = new OpDivide(toPos(t), expr, rhExpr);
			}
			else {
				Assert.isTrue(t.kind == TokenKind.MOD, "Mod token expected");
				expr = new OpModulus(toPos(t), expr, rhExpr);
			}
		}
		return expr;
	}
