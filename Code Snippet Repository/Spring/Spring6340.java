	@Override
	public Reader getCharacterStream() throws SQLException {
		if (this.content != null) {
			return new StringReader(this.content);
		}
		else if (this.characterStream != null) {
			return this.characterStream;
		}
		else {
			return new InputStreamReader(
					(this.asciiStream != null ? this.asciiStream : StreamUtils.emptyInput()),
					StandardCharsets.US_ASCII);
		}
	}
