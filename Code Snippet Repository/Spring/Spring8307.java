	private void contentType(MockHttpServletRequest request) {
		String contentType = header("Content-Type");
		if (contentType == null) {
			FormEncodingType encodingType = this.webRequest.getEncodingType();
			if (encodingType != null) {
				contentType = encodingType.getName();
			}
		}
		request.setContentType(contentType != null ? contentType : MediaType.ALL_VALUE);
	}
