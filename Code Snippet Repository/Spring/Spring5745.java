	private boolean maybeEatLiteral() {
		Token t = peekToken();
		if (t == null) {
			return false;
		}
		if (t.kind == TokenKind.LITERAL_INT) {
			push(Literal.getIntLiteral(t.stringValue(), toPos(t), 10));
		}
		else if (t.kind == TokenKind.LITERAL_LONG) {
			push(Literal.getLongLiteral(t.stringValue(), toPos(t), 10));
		}
		else if (t.kind == TokenKind.LITERAL_HEXINT) {
			push(Literal.getIntLiteral(t.stringValue(), toPos(t), 16));
		}
		else if (t.kind == TokenKind.LITERAL_HEXLONG) {
			push(Literal.getLongLiteral(t.stringValue(), toPos(t), 16));
		}
		else if (t.kind == TokenKind.LITERAL_REAL) {
			push(Literal.getRealLiteral(t.stringValue(), toPos(t), false));
		}
		else if (t.kind == TokenKind.LITERAL_REAL_FLOAT) {
			push(Literal.getRealLiteral(t.stringValue(), toPos(t), true));
		}
		else if (peekIdentifierToken("true")) {
			push(new BooleanLiteral(t.stringValue(), toPos(t), true));
		}
		else if (peekIdentifierToken("false")) {
			push(new BooleanLiteral(t.stringValue(), toPos(t), false));
		}
		else if (t.kind == TokenKind.LITERAL_STRING) {
			push(new StringLiteral(t.stringValue(), toPos(t), t.stringValue()));
		}
		else {
			return false;
		}
		nextToken();
		return true;
	}
