	@Test
	public void handleReturnValueMediaTypeSuffix() throws Exception {
		String body = "Foo";
		MediaType accepted = MediaType.APPLICATION_XHTML_XML;
		List<MediaType> supported = Collections.singletonList(MediaType.valueOf("application/*+xml"));

		servletRequest.addHeader("Accept", accepted);

		given(stringMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringMessageConverter.getSupportedMediaTypes()).willReturn(supported);
		given(stringMessageConverter.canWrite(String.class, accepted)).willReturn(true);

		processor.handleReturnValue(body, returnTypeStringProduces, mavContainer, webRequest);

		assertTrue(mavContainer.isRequestHandled());
		verify(stringMessageConverter).write(eq(body), eq(accepted), isA(HttpOutputMessage.class));
	}
