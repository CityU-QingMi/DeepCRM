	private boolean maybeEatNullReference() {
		if (peekToken(TokenKind.IDENTIFIER)) {
			Token nullToken = peekToken();
			Assert.state(nullToken != null, "Expected token");
			if (!"null".equalsIgnoreCase(nullToken.stringValue())) {
				return false;
			}
			nextToken();
			this.constructedNodes.push(new NullLiteral(toPos(nullToken)));
			return true;
		}
		return false;
	}
