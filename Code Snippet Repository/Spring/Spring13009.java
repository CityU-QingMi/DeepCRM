	@Test(expected = HttpMediaTypeNotAcceptableException.class)
	public void handleReturnValueNotAcceptableProduces() throws Exception {
		MediaType accepted = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Accept", accepted.toString());

		given(stringMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		given(stringMessageConverter.canWrite(String.class, accepted)).willReturn(false);

		processor.handleReturnValue("Foo", returnTypeStringProduces, mavContainer, webRequest);
	}
