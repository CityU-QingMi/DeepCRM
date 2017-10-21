	@Test
	public void handleNoMatch() throws Exception {
		given(this.response.getRawStatusCode()).willReturn(HttpStatus.NOT_FOUND.value());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		given(this.response.getHeaders()).willReturn(responseHeaders);

		byte[] body = "{\"foo\":\"bar\"}".getBytes(StandardCharsets.UTF_8);
		responseHeaders.setContentLength(body.length);
		given(this.response.getBody()).willReturn(new ByteArrayInputStream(body));

		try {
			this.errorHandler.handleError(this.response);
			fail("HttpClientErrorException expected");
		}
		catch (HttpClientErrorException ex) {
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
			assertArrayEquals(body, ex.getResponseBodyAsByteArray());
		}
	}
