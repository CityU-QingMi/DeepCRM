	@Nullable
	public String getContentAsString() throws IllegalStateException, UnsupportedEncodingException {
		Assert.state(this.characterEncoding != null,
				"Cannot get content as a String for a null character encoding. " +
				"Consider setting the characterEncoding in the request.");

		if (this.content == null) {
			return null;
		}
		return new String(this.content, this.characterEncoding);
	}
