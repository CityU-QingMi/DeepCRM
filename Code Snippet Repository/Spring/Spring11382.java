	@Test
	public void defaultSettings() throws Exception {

		RequestedContentTypeResolver resolver = new RequestedContentTypeResolverBuilder().build();
		MockServerHttpRequest request = MockServerHttpRequest.get("/flower").accept(MediaType.IMAGE_GIF).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		List<MediaType> mediaTypes = resolver.resolveMediaTypes(exchange);

		assertEquals(Collections.singletonList(MediaType.IMAGE_GIF), mediaTypes);
	}
