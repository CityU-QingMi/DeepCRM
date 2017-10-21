	private boolean maybeEatTypeReference() {
		if (peekToken(TokenKind.IDENTIFIER)) {
			Token typeName = peekToken();
			Assert.state(typeName != null, "Expected token");
			if (!"T".equals(typeName.stringValue())) {
				return false;
			}
			// It looks like a type reference but is T being used as a map key?
			Token t = takeToken();
			if (peekToken(TokenKind.RSQUARE)) {
				// looks like 'T]' (T is map key)
				push(new PropertyOrFieldReference(false, t.stringValue(), toPos(t)));
				return true;
			}
			eatToken(TokenKind.LPAREN);
			SpelNodeImpl node = eatPossiblyQualifiedId();
			// dotted qualified id
			// Are there array dimensions?
			int dims = 0;
			while (peekToken(TokenKind.LSQUARE, true)) {
				eatToken(TokenKind.RSQUARE);
				dims++;
			}
			eatToken(TokenKind.RPAREN);
			this.constructedNodes.push(new TypeReference(toPos(typeName), node, dims));
			return true;
		}
		return false;
	}
