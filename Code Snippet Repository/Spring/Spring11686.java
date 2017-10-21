	@Test
	public void getHandlerTestInvalidContentType() throws Exception {
		MockServerHttpRequest request = put("/person/1").header("content-type", "bogus").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Object> mono = this.handlerMapping.getHandler(exchange);

		assertError(mono, UnsupportedMediaTypeStatusException.class,
				ex -> assertEquals("Response status 415 with reason \"Invalid mime type \"bogus\": " +
								"does not contain '/'\"", ex.getMessage()));
	}
