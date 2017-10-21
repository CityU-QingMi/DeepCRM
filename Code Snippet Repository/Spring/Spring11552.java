	@Test
	public void toHttpHandlerHandlerThrowResponseStatusExceptionInResponseWriteTo() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction =
				// Mono.<ServerResponse> is required for compilation in Eclipse
				request -> Mono.<ServerResponse> just(new ServerResponse() {
					@Override
					public HttpStatus statusCode() {
						return HttpStatus.OK;
					}

					@Override
					public HttpHeaders headers() {
						return new HttpHeaders();
					}

					@Override
					public Mono<Void> writeTo(ServerWebExchange exchange,
							Context context) {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
					}
				});
		RouterFunction<ServerResponse> routerFunction =
				RouterFunctions.route(RequestPredicates.all(), handlerFunction);

		HttpHandler result = RouterFunctions.toHttpHandler(routerFunction);
		assertNotNull(result);

		MockServerHttpRequest httpRequest = MockServerHttpRequest.get("http://localhost").build();
		MockServerHttpResponse httpResponse = new MockServerHttpResponse();
		result.handle(httpRequest, httpResponse).block();
		assertEquals(HttpStatus.NOT_FOUND, httpResponse.getStatusCode());
	}
