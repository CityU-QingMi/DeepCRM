	@Test
	public void handleReturnTypeResource() throws Exception {
		Resource returnValue = new ByteArrayResource("Content".getBytes(StandardCharsets.UTF_8));

		given(resourceMessageConverter.canWrite(ByteArrayResource.class, null)).willReturn(true);
		given(resourceMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.ALL));
		given(resourceMessageConverter.canWrite(ByteArrayResource.class, MediaType.APPLICATION_OCTET_STREAM)).willReturn(true);

		processor.handleReturnValue(returnValue, returnTypeResource, mavContainer, webRequest);

		then(resourceMessageConverter).should(times(1)).write(any(ByteArrayResource.class),
				eq(MediaType.APPLICATION_OCTET_STREAM), any(HttpOutputMessage.class));
		assertEquals(200, servletResponse.getStatus());
	}
