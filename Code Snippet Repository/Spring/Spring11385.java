	@Test
	public void fixedResolver() throws Exception {

		RequestedContentTypeResolverBuilder builder = new RequestedContentTypeResolverBuilder();
		builder.fixedResolver(MediaType.APPLICATION_JSON);
		RequestedContentTypeResolver resolver = builder.build();

		MockServerHttpRequest request = MockServerHttpRequest.get("/").accept(MediaType.ALL).build();
		List<MediaType> mediaTypes = resolver.resolveMediaTypes(MockServerWebExchange.from(request));

		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), mediaTypes);
	}
