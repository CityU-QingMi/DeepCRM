	@Test
	public void shouldFailHandlingWhenConverterCannotWrite() throws Exception {
		String body = "Foo";
		ResponseEntity<String> returnValue = new ResponseEntity<>(body, HttpStatus.OK);
		MediaType accepted = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Accept", accepted.toString());

		given(stringHttpMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringHttpMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		given(stringHttpMessageConverter.canWrite(String.class, accepted)).willReturn(false);

		this.thrown.expect(HttpMediaTypeNotAcceptableException.class);
		processor.handleReturnValue(returnValue, returnTypeResponseEntityProduces, mavContainer, webRequest);
	}
