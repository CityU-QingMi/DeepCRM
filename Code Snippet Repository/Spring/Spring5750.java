	private boolean peekToken(TokenKind desiredTokenKind, boolean consumeIfMatched) {
		Token t = peekToken();
		if (t == null) {
			return false;
		}
		if (t.kind == desiredTokenKind) {
			if (consumeIfMatched) {
				this.tokenStreamPointer++;
			}
			return true;
		}

		if (desiredTokenKind == TokenKind.IDENTIFIER) {
			// Might be one of the textual forms of the operators (e.g. NE for != ) -
			// in which case we can treat it as an identifier. The list is represented here:
			// Tokenizer.alternativeOperatorNames and those ones are in order in the TokenKind enum.
			if (t.kind.ordinal() >= TokenKind.DIV.ordinal() && t.kind.ordinal() <= TokenKind.NOT.ordinal() &&
					t.data != null) {
				// if t.data were null, we'd know it wasn't the textual form, it was the symbol form
				return true;
			}
		}
		return false;
	}
