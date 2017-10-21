	private Charset determineCharset() {
		MediaType contentType = getHeaders().getContentType();
		if (contentType != null) {
			Charset charset = contentType.getCharset();
			if (charset != null) {
				return charset;
			}
		}
		String encoding = this.multipartRequest.getCharacterEncoding();
		return (encoding != null ? Charset.forName(encoding) : FORM_CHARSET);
	}
