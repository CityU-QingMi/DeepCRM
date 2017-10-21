	@Test
	public void getForEntity() throws Exception {
		given(converter.canRead(String.class, null)).willReturn(true);
		MediaType textPlain = new MediaType("text", "plain");
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(textPlain));
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.GET)).willReturn(request);
		HttpHeaders requestHeaders = new HttpHeaders();
		given(request.getHeaders()).willReturn(requestHeaders);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		String expected = "Hello World";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(textPlain);
		responseHeaders.setContentLength(10);
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getStatusText()).willReturn(HttpStatus.OK.getReasonPhrase());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream(expected.getBytes()));
		given(converter.canRead(String.class, textPlain)).willReturn(true);
		given(converter.read(eq(String.class), any(HttpInputMessage.class))).willReturn(expected);

		ResponseEntity<String> result = template.getForEntity("http://example.com", String.class);
		assertEquals("Invalid GET result", expected, result.getBody());
		assertEquals("Invalid Accept header", textPlain.toString(), requestHeaders.getFirst("Accept"));
		assertEquals("Invalid Content-Type header", textPlain, result.getHeaders().getContentType());
		assertEquals("Invalid status code", HttpStatus.OK, result.getStatusCode());

		verify(response).close();
	}
