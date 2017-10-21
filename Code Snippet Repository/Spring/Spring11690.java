	@Test
	public void handleMatchBestMatchingPatternAttribute() throws Exception {
		RequestMappingInfo key = paths("/{path1}/2", "/**").build();
		ServerWebExchange exchange = MockServerWebExchange.from(get("/1/2").build());
		this.handlerMapping.handleMatch(key, handlerMethod, exchange);

		PathPattern bestMatch = (PathPattern) exchange.getAttributes().get(BEST_MATCHING_PATTERN_ATTRIBUTE);
		assertEquals("/{path1}/2", bestMatch.getPatternString());

		HandlerMethod mapped = (HandlerMethod) exchange.getAttributes().get(BEST_MATCHING_HANDLER_ATTRIBUTE);
		assertSame(handlerMethod, mapped);
	}
