	@Test
	public void toHttpHandlerNormal() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction = request -> ServerResponse.accepted().build();
		RouterFunction<ServerResponse> routerFunction =
				RouterFunctions.route(RequestPredicates.all(), handlerFunction);

		HttpHandler result = RouterFunctions.toHttpHandler(routerFunction);
		assertNotNull(result);

		MockServerHttpRequest httpRequest = MockServerHttpRequest.get("http://localhost").build();
		MockServerHttpResponse httpResponse = new MockServerHttpResponse();
		result.handle(httpRequest, httpResponse).block();
		assertEquals(HttpStatus.ACCEPTED, httpResponse.getStatusCode());
	}
