	@Test
	public void resolveKeyFromRegistrations() throws Exception {
		ServerWebExchange exchange = createExchange("html");

		Map<String, MediaType> mapping = Collections.emptyMap();
		RequestedContentTypeResolver resolver = new ParameterContentTypeResolver(mapping);
		List<MediaType> mediaTypes = resolver.resolveMediaTypes(exchange);
		assertEquals(Collections.singletonList(new MediaType("text", "html")), mediaTypes);

		mapping = Collections.singletonMap("HTML", MediaType.APPLICATION_XHTML_XML);
		resolver = new ParameterContentTypeResolver(mapping);
		mediaTypes = resolver.resolveMediaTypes(exchange);
		assertEquals(Collections.singletonList(new MediaType("application", "xhtml+xml")), mediaTypes);
	}
