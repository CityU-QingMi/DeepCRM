	@Test
	public void shouldFailHandlingWhenContentTypeNotSupported() throws Exception {
		String body = "Foo";
		ResponseEntity<String> returnValue = new ResponseEntity<>(body, HttpStatus.OK);
		MediaType accepted = MediaType.APPLICATION_ATOM_XML;
		servletRequest.addHeader("Accept", accepted.toString());

		given(stringHttpMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringHttpMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));

		this.thrown.expect(HttpMediaTypeNotAcceptableException.class);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);
	}
