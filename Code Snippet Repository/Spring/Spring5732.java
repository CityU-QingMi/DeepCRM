	private void consumeArguments(List<SpelNodeImpl> accumulatedArguments) {
		Token t = peekToken();
		Assert.state(t != null, "Expected token");
		int pos = t.startPos;
		Token next;
		do {
			nextToken();  // consume (first time through) or comma (subsequent times)
			t = peekToken();
			if (t == null) {
				throw internalException(pos, SpelMessage.RUN_OUT_OF_ARGUMENTS);
			}
			if (t.kind != TokenKind.RPAREN) {
				accumulatedArguments.add(eatExpression());
			}
			next = peekToken();
		}
		while (next != null && next.kind == TokenKind.COMMA);

		if (next == null) {
			throw internalException(pos, SpelMessage.RUN_OUT_OF_ARGUMENTS);
		}
	}
