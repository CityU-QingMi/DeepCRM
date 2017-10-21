	private void processTokenArray(JsonToken token, List<TokenBuffer> result) throws IOException {
		if (!isTopLevelArrayToken(token)) {
			this.tokenBuffer.copyCurrentEvent(this.parser);
		}

		if (token == JsonToken.END_OBJECT && this.objectDepth == 0 &&
				(this.arrayDepth == 1 || this.arrayDepth == 0)) {
			result.add(this.tokenBuffer);
			this.tokenBuffer = new TokenBuffer(this.parser);
		}
	}
