	@Nullable
	private SpelNodeImpl eatExpression() {
		SpelNodeImpl expr = eatLogicalOrExpression();
		Token t = peekToken();
		if (t != null) {
			if (t.kind == TokenKind.ASSIGN) {  // a=b
				if (expr == null) {
					expr = new NullLiteral(toPos(t.startPos - 1, t.endPos - 1));
				}
				nextToken();
				SpelNodeImpl assignedValue = eatLogicalOrExpression();
				return new Assign(toPos(t), expr, assignedValue);
			}
			if (t.kind == TokenKind.ELVIS) {  // a?:b (a if it isn't null, otherwise b)
				if (expr == null) {
					expr = new NullLiteral(toPos(t.startPos - 1, t.endPos - 2));
				}
				nextToken();  // elvis has left the building
				SpelNodeImpl valueIfNull = eatExpression();
				if (valueIfNull == null) {
					valueIfNull = new NullLiteral(toPos(t.startPos + 1, t.endPos + 1));
				}
				return new Elvis(toPos(t), expr, valueIfNull);
			}
			if (t.kind == TokenKind.QMARK) {  // a?b:c
				if (expr == null) {
					expr = new NullLiteral(toPos(t.startPos - 1, t.endPos - 1));
				}
				nextToken();
				SpelNodeImpl ifTrueExprValue = eatExpression();
				eatToken(TokenKind.COLON);
				SpelNodeImpl ifFalseExprValue = eatExpression();
				return new Ternary(toPos(t), expr, ifTrueExprValue, ifFalseExprValue);
			}
		}
		return expr;
	}
