	@Nullable
	private SpelNodeImpl eatRelationalExpression() {
		SpelNodeImpl expr = eatSumExpression();
		Token relationalOperatorToken = maybeEatRelationalOperator();
		if (relationalOperatorToken != null) {
			Token t = takeToken();  // consume relational operator token
			SpelNodeImpl rhExpr = eatSumExpression();
			checkOperands(t, expr, rhExpr);
			TokenKind tk = relationalOperatorToken.kind;

			if (relationalOperatorToken.isNumericRelationalOperator()) {
				int pos = toPos(t);
				if (tk == TokenKind.GT) {
					return new OpGT(pos, expr, rhExpr);
				}
				if (tk == TokenKind.LT) {
					return new OpLT(pos, expr, rhExpr);
				}
				if (tk == TokenKind.LE) {
					return new OpLE(pos, expr, rhExpr);
				}
				if (tk == TokenKind.GE) {
					return new OpGE(pos, expr, rhExpr);
				}
				if (tk == TokenKind.EQ) {
					return new OpEQ(pos, expr, rhExpr);
				}
				Assert.isTrue(tk == TokenKind.NE, "Not-equals token expected");
				return new OpNE(pos, expr, rhExpr);
			}

			if (tk == TokenKind.INSTANCEOF) {
				return new OperatorInstanceof(toPos(t), expr, rhExpr);
			}

			if (tk == TokenKind.MATCHES) {
				return new OperatorMatches(toPos(t), expr, rhExpr);
			}

			Assert.isTrue(tk == TokenKind.BETWEEN, "Between token expected");
			return new OperatorBetween(toPos(t), expr, rhExpr);
		}
		return expr;
	}
