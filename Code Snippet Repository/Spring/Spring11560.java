	@Test
	public void toHttpHandlerHandlerResponseStatusException() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction =
				request -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
		RouterFunction<ServerResponse> routerFunction =
				RouterFunctions.route(RequestPredicates.all(), handlerFunction);

		HttpHandler result = RouterFunctions.toHttpHandler(routerFunction);
		assertNotNull(result);

		MockServerHttpRequest httpRequest = MockServerHttpRequest.get("http://localhost").build();
		MockServerHttpResponse httpResponse = new MockServerHttpResponse();
		result.handle(httpRequest, httpResponse).block();
		assertEquals(HttpStatus.NOT_FOUND, httpResponse.getStatusCode());
	}
