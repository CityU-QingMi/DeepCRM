	private MultiValueMap<String, String> parseFormData(final MediaType mediaType) {
		HttpInputMessage message = new HttpInputMessage() {
			@Override
			public InputStream getBody() throws IOException {
				return (content != null ? new ByteArrayInputStream(content) : StreamUtils.emptyInput());
			}
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(mediaType);
				return headers;
			}
		};

		try {
			return new FormHttpMessageConverter().read(null, message);
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to parse form data in request body", ex);
		}
	}
