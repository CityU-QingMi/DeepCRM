	private void lexIdentifier() {
		int start = this.pos;
		do {
			this.pos++;
		}
		while (isIdentifier(this.toProcess[this.pos]));
		char[] subarray = subarray(start, this.pos);

		// Check if this is the alternative (textual) representation of an operator (see
		// alternativeOperatorNames)
		if ((this.pos - start) == 2 || (this.pos - start) == 3) {
			String asString = new String(subarray).toUpperCase();
			int idx = Arrays.binarySearch(ALTERNATIVE_OPERATOR_NAMES, asString);
			if (idx >= 0) {
				pushOneCharOrTwoCharToken(TokenKind.valueOf(asString), start, subarray);
				return;
			}
		}
		this.tokens.add(new Token(TokenKind.IDENTIFIER, subarray, start, this.pos));
	}
