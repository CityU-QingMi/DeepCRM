	@Test
	@SuppressWarnings("")
	public void shouldHandleReturnValueWithResponseBodyAdvice() throws Exception {
		servletRequest.addHeader("Accept", "text/*");
		servletRequest.setAttribute(PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Collections.singleton(MediaType.TEXT_HTML));
		ResponseEntity<String> returnValue = new ResponseEntity<>(HttpStatus.OK);
		ResponseBodyAdvice<String> advice = mock(ResponseBodyAdvice.class);
		given(advice.supports(any(), any())).willReturn(true);
		given(advice.beforeBodyWrite(any(), any(), any(), any(), any(), any())).willReturn("Foo");

		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(
				Collections.singletonList(stringHttpMessageConverter), null, Collections.singletonList(advice));

		reset(stringHttpMessageConverter);
		given(stringHttpMessageConverter.canWrite(String.class, MediaType.TEXT_HTML)).willReturn(true);

		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertTrue(mavContainer.isRequestHandled());
		verify(stringHttpMessageConverter).write(eq("Foo"), eq(MediaType.TEXT_HTML), isA(HttpOutputMessage.class));
	}
