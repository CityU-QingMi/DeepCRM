	@Test
	public void created() throws Exception {
		URI location = new URI("/foo");
		DefaultResponseCreator responseCreator = MockRestResponseCreators.withCreatedEntity(location);
		MockClientHttpResponse response = (MockClientHttpResponse) responseCreator.createResponse(null);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(location, response.getHeaders().getLocation());
		assertEquals(0, StreamUtils.copyToByteArray(response.getBody()).length);
	}
