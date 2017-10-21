	@Test
	public void handleNoMatchOverride() throws Exception {
		this.errorHandler.setSeriesMapping(Collections
				.singletonMap(HttpStatus.Series.CLIENT_ERROR, null));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.NOT_FOUND.value());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		given(this.response.getHeaders()).willReturn(responseHeaders);

		byte[] body = "{\"foo\":\"bar\"}".getBytes(StandardCharsets.UTF_8);
		responseHeaders.setContentLength(body.length);
		given(this.response.getBody()).willReturn(new ByteArrayInputStream(body));

		this.errorHandler.handleError(this.response);
	}
