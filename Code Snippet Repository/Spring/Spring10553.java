	@Test
	public void errorHandling() throws Exception {
		URI uri = new URI("http://example.com");
		given(requestFactory.createRequest(uri, HttpMethod.GET)).willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(true);
		given(response.getStatusCode()).willReturn(HttpStatus.INTERNAL_SERVER_ERROR);
		given(response.getStatusText()).willReturn("Internal Server Error");
		willThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
				.given(errorHandler).handleError(uri, HttpMethod.GET, response);

		try {
			template.execute("http://example.com", HttpMethod.GET, null, null);
			fail("HttpServerErrorException expected");
		}
		catch (HttpServerErrorException ex) {
			// expected
		}

		verify(response).close();
	}
