	@Test
	@SuppressWarnings("")
	public void converterThrowsIOException() throws IOException {
		HttpMessageConverter<String> converter = mock(HttpMessageConverter.class);
		HttpHeaders responseHeaders = new HttpHeaders();
		MediaType contentType = MediaType.TEXT_PLAIN;
		responseHeaders.setContentType(contentType);
		extractor = new HttpMessageConverterExtractor<>(String.class, createConverterList(converter));
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream("Foobar".getBytes()));
		given(converter.canRead(String.class, contentType)).willThrow(IOException.class);
		exception.expect(RestClientException.class);
		exception.expectMessage("Error while extracting response for type " +
				"[class java.lang.String] and content type [text/plain]");
		exception.expectCause(Matchers.instanceOf(IOException.class));

		extractor.extractData(response);
	}
