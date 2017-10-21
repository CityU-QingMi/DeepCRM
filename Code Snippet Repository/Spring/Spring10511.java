	@Test
	@SuppressWarnings("")
	public void cannotRead() throws IOException {
		HttpMessageConverter<String> converter = mock(HttpMessageConverter.class);
		HttpHeaders responseHeaders = new HttpHeaders();
		MediaType contentType = MediaType.TEXT_PLAIN;
		responseHeaders.setContentType(contentType);
		extractor = new HttpMessageConverterExtractor<>(String.class, createConverterList(converter));
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream("Foobar".getBytes()));
		given(converter.canRead(String.class, contentType)).willReturn(false);
		exception.expect(RestClientException.class);

		extractor.extractData(response);
	}
