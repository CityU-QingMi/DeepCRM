	@Test
	public void postForEntity() throws Exception {
		MediaType textPlain = new MediaType("text", "plain");
		given(converter.canRead(Integer.class, null)).willReturn(true);
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(textPlain));
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.POST)).willReturn(this.request);
		HttpHeaders requestHeaders = new HttpHeaders();
		given(this.request.getHeaders()).willReturn(requestHeaders);
		String request = "Hello World";
		given(converter.canWrite(String.class, null)).willReturn(true);
		converter.write(request, null, this.request);
		given(this.request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		Integer expected = 42;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(textPlain);
		responseHeaders.setContentLength(10);
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getStatusText()).willReturn(HttpStatus.OK.getReasonPhrase());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream(expected.toString().getBytes()));
		given(converter.canRead(Integer.class, textPlain)).willReturn(true);
		given(converter.read(eq(Integer.class), any(HttpInputMessage.class))).willReturn(expected);

		ResponseEntity<Integer> result = template.postForEntity("http://example.com", request, Integer.class);
		assertEquals("Invalid POST result", expected, result.getBody());
		assertEquals("Invalid Content-Type", textPlain, result.getHeaders().getContentType());
		assertEquals("Invalid Accept header", textPlain.toString(), requestHeaders.getFirst("Accept"));
		assertEquals("Invalid status code", HttpStatus.OK, result.getStatusCode());

		verify(response).close();
	}
