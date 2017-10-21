	@Test
	public void patchForObject() throws Exception {
		MediaType textPlain = new MediaType("text", "plain");
		given(converter.canRead(Integer.class, null)).willReturn(true);
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(textPlain));
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.PATCH)).willReturn(this.request);
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
		given(response.getStatusCode()).willReturn(HttpStatus.OK);
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream(expected.toString().getBytes()));
		given(converter.canRead(Integer.class, textPlain)).willReturn(true);
		given(converter.read(eq(Integer.class), any(HttpInputMessage.class))).willReturn(expected);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		Integer result = template.patchForObject("http://example.com", request, Integer.class);
		assertEquals("Invalid POST result", expected, result);
		assertEquals("Invalid Accept header", textPlain.toString(), requestHeaders.getFirst("Accept"));

		verify(response).close();
	}
