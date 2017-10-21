	@Test
	@SuppressWarnings("")
	public void exchangeParameterizedType() throws Exception {
		GenericHttpMessageConverter converter = mock(GenericHttpMessageConverter.class);
		template.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(converter));

		ParameterizedTypeReference<List<Integer>> intList = new ParameterizedTypeReference<List<Integer>>() {};
		given(converter.canRead(intList.getType(), null, null)).willReturn(true);
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.POST)).willReturn(this.request);
		HttpHeaders requestHeaders = new HttpHeaders();
		given(this.request.getHeaders()).willReturn(requestHeaders);
		given(converter.canWrite(String.class, String.class, null)).willReturn(true);
		String requestBody = "Hello World";
		converter.write(requestBody, String.class, null, this.request);
		given(this.request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		List<Integer> expected = Collections.singletonList(42);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_PLAIN);
		responseHeaders.setContentLength(10);
		given(response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		given(response.getStatusText()).willReturn(HttpStatus.OK.getReasonPhrase());
		given(response.getHeaders()).willReturn(responseHeaders);
		given(response.getBody()).willReturn(new ByteArrayInputStream(Integer.toString(42).getBytes()));
		given(converter.canRead(intList.getType(), null, MediaType.TEXT_PLAIN)).willReturn(true);
		given(converter.read(eq(intList.getType()), eq(null), any(HttpInputMessage.class))).willReturn(expected);

		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.set("MyHeader", "MyValue");
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, entityHeaders);
		ResponseEntity<List<Integer>> result = template.exchange("http://example.com", HttpMethod.POST, requestEntity, intList);
		assertEquals("Invalid POST result", expected, result.getBody());
		assertEquals("Invalid Content-Type", MediaType.TEXT_PLAIN, result.getHeaders().getContentType());
		assertEquals("Invalid Accept header", MediaType.TEXT_PLAIN_VALUE, requestHeaders.getFirst("Accept"));
		assertEquals("Invalid custom header", "MyValue", requestHeaders.getFirst("MyHeader"));
		assertEquals("Invalid status code", HttpStatus.OK, result.getStatusCode());

		verify(response).close();
	}
