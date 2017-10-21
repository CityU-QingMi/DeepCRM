	@Test
	public void webExceptionHandler() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/unknown-argument-type").build());

		List<WebExceptionHandler> handlers = Collections.singletonList(new ServerError500ExceptionHandler());
		WebHandler webHandler = new ExceptionHandlingWebHandler(this.dispatcherHandler, handlers);
		webHandler.handle(exchange).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exchange.getResponse().getStatusCode());
	}
