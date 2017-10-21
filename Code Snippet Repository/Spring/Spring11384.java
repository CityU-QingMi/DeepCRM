	@Test
	public void parameterResolverWithCustomParamName() throws Exception {

		RequestedContentTypeResolverBuilder builder = new RequestedContentTypeResolverBuilder();
		builder.parameterResolver().mediaType("json", MediaType.APPLICATION_JSON).parameterName("s");
		RequestedContentTypeResolver resolver = builder.build();

		MockServerHttpRequest request = MockServerHttpRequest.get("/flower?s=json").build();
		List<MediaType> mediaTypes = resolver.resolveMediaTypes(MockServerWebExchange.from(request));

		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), mediaTypes);
	}
