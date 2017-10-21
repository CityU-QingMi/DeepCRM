	@Test
	public void resolver() throws Exception {

		RequestedContentTypeResolverBuilder builder = new RequestedContentTypeResolverBuilder();
		builder.resolver(new FixedContentTypeResolver(MediaType.APPLICATION_JSON));
		RequestedContentTypeResolver resolver = builder.build();

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		List<MediaType> mediaTypes = resolver.resolveMediaTypes(exchange);
		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), mediaTypes);

		exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").accept(MediaType.ALL).build());
		mediaTypes = resolver.resolveMediaTypes(exchange);
		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), mediaTypes);
	}
