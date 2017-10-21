	@Test
	public void queryParameters() throws Exception {
		URI uri = new URI(baseUrl + "/params?param1=value&param2=value1&param2=value2");
		ClientHttpRequest request = factory.createRequest(uri, HttpMethod.GET);

		ClientHttpResponse response = request.execute();
		try {
			assertEquals("Invalid status code", HttpStatus.OK, response.getStatusCode());
		}
		finally {
			response.close();
		}
	}
