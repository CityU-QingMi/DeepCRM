	private void processTokenNormal(JsonToken token, List<TokenBuffer> result) throws IOException {
		this.tokenBuffer.copyCurrentEvent(this.parser);

		if (token == JsonToken.END_OBJECT || token == JsonToken.END_ARRAY) {
			if (this.objectDepth == 0 && this.arrayDepth == 0) {
				result.add(this.tokenBuffer);
				this.tokenBuffer = new TokenBuffer(this.parser);
			}
		}

	}
