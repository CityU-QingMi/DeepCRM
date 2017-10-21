	@Test
	public void httpHeaders() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAllow(new LinkedHashSet<>(Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)));
		MethodParameter returnType = on(TestController.class).resolveReturnType(entity(Void.class));
		HandlerResult result = handlerResult(headers, returnType);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.OK, exchange.getResponse().getStatusCode());
		assertEquals(1, exchange.getResponse().getHeaders().size());
		assertEquals("GET,POST,OPTIONS", exchange.getResponse().getHeaders().getFirst("Allow"));
		assertResponseBodyIsEmpty(exchange);
	}
