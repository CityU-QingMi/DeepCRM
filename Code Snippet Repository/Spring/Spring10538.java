	@Test
	public void postForEntityNull() throws Exception {
		MediaType textPlain = new MediaType("text", "plain");
		given(converter.canRead(Integer.class, null)).willReturn(true);
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(textPlain));
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.POST)).willReturn(request);
		HttpHeaders requestHeaders = new HttpHeaders();
		given(request.getHeaders()).willReturn(requestHeaders);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(textPlain);
		responseHeaders.setContentLength(10);
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getStatusText()).willReturn(HttpStatus.OK.getReasonPhrase());
		given(response.getBody()).willReturn(StreamUtils.emptyInput());
		given(converter.canRead(Integer.class, textPlain)).willReturn(true);
		given(converter.read(Integer.class, response)).willReturn(null);

		ResponseEntity<Integer> result = template.postForEntity("http://example.com", null, Integer.class);
		assertFalse("Invalid POST result", result.hasBody());
		assertEquals("Invalid Content-Type", textPlain, result.getHeaders().getContentType());
		assertEquals("Invalid content length", 0, requestHeaders.getContentLength());
		assertEquals("Invalid status code", HttpStatus.OK, result.getStatusCode());

		verify(response).close();
	}
