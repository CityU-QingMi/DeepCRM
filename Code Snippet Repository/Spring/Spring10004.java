	@Test
	public void status() throws Exception {
		URI uri = new URI(baseUrl + "/status/notfound");
		ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);
		assertEquals("Invalid HTTP method", HttpMethod.GET, request.getMethod());
		assertEquals("Invalid HTTP URI", uri, request.getURI());
		ClientHttpResponse response = request.execute();
		try {
			assertEquals("Invalid status code", HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		finally {
			response.close();
		}
	}
