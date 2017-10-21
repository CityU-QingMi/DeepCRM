	@Test
	public void handleMatchUriTemplateVariablesDecode() throws Exception {
		RequestMappingInfo key = paths("/{group}/{identifier}").build();
		URI url = URI.create("/group/a%2Fb");
		ServerWebExchange exchange = MockServerWebExchange.from(method(HttpMethod.GET, url).build());

		this.handlerMapping.handleMatch(key, handlerMethod, exchange);

		String name = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		@SuppressWarnings("unchecked")
		Map<String, String> uriVariables = (Map<String, String>) exchange.getAttributes().get(name);

		assertNotNull(uriVariables);
		assertEquals("group", uriVariables.get("group"));
		assertEquals("a/b", uriVariables.get("identifier"));
	}
