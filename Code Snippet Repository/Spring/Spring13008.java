	@Test(expected = HttpMediaTypeNotAcceptableException.class)
	public void handleReturnValueNotAcceptable() throws Exception {
		MediaType accepted = MediaType.APPLICATION_ATOM_XML;
		servletRequest.addHeader("Accept", accepted.toString());

		given(stringMessageConverter.canWrite(String.class, null)).willReturn(true);
		given(stringMessageConverter.getSupportedMediaTypes()).willReturn(Arrays.asList(MediaType.TEXT_PLAIN));
		given(stringMessageConverter.canWrite(String.class, accepted)).willReturn(false);

		processor.handleReturnValue("Foo", returnTypeString, mavContainer, webRequest);
	}
