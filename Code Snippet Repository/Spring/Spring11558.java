	@Test
	public void toHttpHandlerHandlerThrowsException() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction =
				request -> {
					throw new IllegalStateException();
				};
		RouterFunction<ServerResponse> routerFunction =
				RouterFunctions.route(RequestPredicates.all(), handlerFunction);

		HttpHandler result = RouterFunctions.toHttpHandler(routerFunction);
		assertNotNull(result);

		MockServerHttpRequest httpRequest = MockServerHttpRequest.get("http://localhost").build();
		MockServerHttpResponse httpResponse = new MockServerHttpResponse();
		result.handle(httpRequest, httpResponse).block();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, httpResponse.getStatusCode());
	}
