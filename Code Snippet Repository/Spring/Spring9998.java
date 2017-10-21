	@Test
	public void status() throws Exception {
		URI uri = new URI(baseUrl + "/status/notfound");
		AsyncClientHttpRequest request = this.factory.createAsyncRequest(uri, HttpMethod.GET);
		assertEquals("Invalid HTTP method", HttpMethod.GET, request.getMethod());
		assertEquals("Invalid HTTP URI", uri, request.getURI());
		Future<ClientHttpResponse> futureResponse = request.executeAsync();
		ClientHttpResponse response = futureResponse.get();
		try {
			assertEquals("Invalid status code", HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		finally {
			response.close();
		}
	}
