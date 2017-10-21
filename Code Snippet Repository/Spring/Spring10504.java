	@Test
	public void handleErrorStatusMatch() throws Exception {
		given(this.response.getRawStatusCode()).willReturn(HttpStatus.I_AM_A_TEAPOT.value());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		given(this.response.getHeaders()).willReturn(responseHeaders);

		byte[] body = "{\"foo\":\"bar\"}".getBytes(StandardCharsets.UTF_8);
		responseHeaders.setContentLength(body.length);
		given(this.response.getBody()).willReturn(new ByteArrayInputStream(body));

		try {
			this.errorHandler.handleError(this.response);
			fail("MyRestClientException expected");
		}
		catch (MyRestClientException ex) {
			assertEquals("bar", ex.getFoo());
		}
	}
