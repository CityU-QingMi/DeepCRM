	@Test
	public void handleReturnValue() throws Exception {
		MediaType accepted = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Accept", accepted.toString());

		String body = "Foo";
		given(stringMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		given(stringMessageConverter.canWrite(String.class, accepted)).willReturn(true);

		processor.handleReturnValue(body, returnTypeString, mavContainer, webRequest);

		assertTrue("The requestHandled flag wasn't set", mavContainer.isRequestHandled());
		verify(stringMessageConverter).write(eq(body), eq(accepted), isA(HttpOutputMessage.class));
	}
