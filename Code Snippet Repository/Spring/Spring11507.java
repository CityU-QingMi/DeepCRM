	@Test
	public void buildVoidPublisher() throws Exception {
		Mono<Void> mono = Mono.empty();
		Mono<ServerResponse> result = ServerResponse.ok().build(mono);

		ServerWebExchange exchange = mock(ServerWebExchange.class);
		MockServerHttpResponse response = new MockServerHttpResponse();
		when(exchange.getResponse()).thenReturn(response);
		ServerResponse.Context context = mock(ServerResponse.Context.class);

		result.flatMap(res -> res.writeTo(exchange, context)).block();

		StepVerifier.create(response.getBody()).expectComplete().verify();
	}
