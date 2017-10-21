	@Test
	public void handleError() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		given(response.getRawStatusCode()).willReturn(HttpStatus.NOT_FOUND.value());
		given(response.getStatusText()).willReturn("Not Found");
		given(response.getHeaders()).willReturn(headers);
		given(response.getBody()).willReturn(new ByteArrayInputStream("Hello World".getBytes("UTF-8")));

		try {
			handler.handleError(response);
			fail("expected HttpClientErrorException");
		}
		catch (HttpClientErrorException e) {
			assertSame(headers, e.getResponseHeaders());
		}
	}
