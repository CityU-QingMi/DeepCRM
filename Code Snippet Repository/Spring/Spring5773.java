	private void pushHexIntToken(char[] data, boolean isLong, int start, int end) {
		if (data.length == 0) {
			if (isLong) {
				throw new InternalParseException(new SpelParseException(this.expressionString,
						start, SpelMessage.NOT_A_LONG, this.expressionString.substring(start,
								end + 1)));
			}
			else {
				throw new InternalParseException(new SpelParseException(this.expressionString,
						start, SpelMessage.NOT_AN_INTEGER, this.expressionString.substring(
								start, end)));
			}
		}
		if (isLong) {
			this.tokens.add(new Token(TokenKind.LITERAL_HEXLONG, data, start, end));
		}
		else {
			this.tokens.add(new Token(TokenKind.LITERAL_HEXINT, data, start, end));
		}
	}
