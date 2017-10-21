	public RequestMatcher formData(final MultiValueMap<String, String> expectedContent) {
		return request -> {
			HttpInputMessage inputMessage = new HttpInputMessage() {
				@Override
				public InputStream getBody() throws IOException {
					MockClientHttpRequest mockRequest = (MockClientHttpRequest) request;
					return new ByteArrayInputStream(mockRequest.getBodyAsBytes());
				}
				@Override
				public HttpHeaders getHeaders() {
					return request.getHeaders();
				}
			};
			FormHttpMessageConverter converter = new FormHttpMessageConverter();
			assertEquals("Request content", expectedContent, converter.read(null, inputMessage));
		};
	}
