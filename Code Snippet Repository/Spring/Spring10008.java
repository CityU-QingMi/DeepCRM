	protected void assertHttpMethod(String path, HttpMethod method) throws Exception {
		ClientHttpResponse response = null;
		try {
			ClientHttpRequest request = factory.createRequest(new URI(baseUrl + "/methods/" + path), method);
			if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH) {
				// requires a body
				try {
					request.getBody().write(32);
				}
				catch (UnsupportedOperationException ex) {
					// probably a streaming request - let's simply ignore it
				}
			}
			response = request.execute();
			assertEquals("Invalid response status", HttpStatus.OK, response.getStatusCode());
			assertEquals("Invalid method", path.toUpperCase(Locale.ENGLISH), request.getMethod().name());
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}
