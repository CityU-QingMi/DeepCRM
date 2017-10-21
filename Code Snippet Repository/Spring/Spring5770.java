	private void lexQuotedStringLiteral() {
		int start = this.pos;
		boolean terminated = false;
		while (!terminated) {
			this.pos++;
			char ch = this.toProcess[this.pos];
			if (ch == '\'') {
				// may not be the end if the char after is also a '
				if (this.toProcess[this.pos + 1] == '\'') {
					this.pos++; // skip over that too, and continue
				}
				else {
					terminated = true;
				}
			}
			if (ch == 0) {
				throw new InternalParseException(new SpelParseException(this.expressionString, start,
						SpelMessage.NON_TERMINATING_QUOTED_STRING));
			}
		}
		this.pos++;
		this.tokens.add(new Token(TokenKind.LITERAL_STRING, subarray(start, this.pos), start, this.pos));
	}
