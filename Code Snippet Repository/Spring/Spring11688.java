	@Test
	@SuppressWarnings("")
	public void handleMatchUriTemplateVariables() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(get("/1/2").build());

		RequestMappingInfo key = paths("/{path1}/{path2}").build();
		this.handlerMapping.handleMatch(key, handlerMethod, exchange);

		String name = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		Map<String, String> uriVariables = (Map<String, String>) exchange.getAttributes().get(name);

		assertNotNull(uriVariables);
		assertEquals("1", uriVariables.get("path1"));
		assertEquals("2", uriVariables.get("path2"));
	}
