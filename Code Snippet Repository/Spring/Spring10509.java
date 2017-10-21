	@Test
	@SuppressWarnings("")
	public void emptyMessageBody() throws IOException {
		HttpMessageConverter<String> converter = mock(HttpMessageConverter.class);
		HttpHeaders responseHeaders = new HttpHeaders();
		extractor = new HttpMessageConverterExtractor<>(String.class, createConverterList(converter));
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream("".getBytes()));

		Object result = extractor.extractData(response);
		assertNull(result);
	}
