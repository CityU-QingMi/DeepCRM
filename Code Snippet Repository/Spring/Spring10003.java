	protected void assertHttpMethod(String path, HttpMethod method) throws Exception {
		ClientHttpResponse response = null;
		try {
			AsyncClientHttpRequest request = this.factory.createAsyncRequest(new URI(baseUrl + "/methods/" + path), method);
			if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH) {
				// requires a body
				request.getBody().write(32);
			}
			Future<ClientHttpResponse> futureResponse = request.executeAsync();
			response = futureResponse.get();
			assertEquals("Invalid response status", HttpStatus.OK, response.getStatusCode());
			assertEquals("Invalid method", path.toUpperCase(Locale.ENGLISH), request.getMethod().name());
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}
