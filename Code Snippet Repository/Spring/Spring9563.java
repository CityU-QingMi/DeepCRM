	public String getResponseBodyAsString() {
		if (this.responseCharset == null) {
			return new String(this.responseBody, DEFAULT_CHARSET);
		}
		try {
			return new String(this.responseBody, this.responseCharset);
		}
		catch (UnsupportedEncodingException ex) {
			// should not occur
			throw new IllegalStateException(ex);
		}
	}
