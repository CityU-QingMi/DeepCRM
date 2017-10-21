	@Test
	public void toHttpHandlerWebFilter() throws Exception {
		AtomicBoolean filterInvoked = new AtomicBoolean();

		WebFilter webFilter = new WebFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
				filterInvoked.set(true);
				return chain.filter(exchange);
			}
		};

		HandlerFunction<ServerResponse> handlerFunction = request -> ServerResponse.accepted().build();
		RouterFunction<ServerResponse> routerFunction =
				RouterFunctions.route(RequestPredicates.all(), handlerFunction);

		HandlerStrategies handlerStrategies = HandlerStrategies.builder()
				.webFilter(webFilter).build();

		HttpHandler result = RouterFunctions.toHttpHandler(routerFunction, handlerStrategies);
		assertNotNull(result);

		MockServerHttpRequest httpRequest = MockServerHttpRequest.get("http://localhost").build();
		MockServerHttpResponse httpResponse = new MockServerHttpResponse();
		result.handle(httpRequest, httpResponse).block();
		assertEquals(HttpStatus.ACCEPTED, httpResponse.getStatusCode());

		assertTrue(filterInvoked.get());
	}
