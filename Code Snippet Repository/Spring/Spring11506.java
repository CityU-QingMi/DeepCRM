	@Test
	public void build() throws Exception {
		Mono<ServerResponse>
				result = ServerResponse.status(HttpStatus.CREATED).header("MyKey", "MyValue").build();

		ServerWebExchange exchange = mock(ServerWebExchange.class);
		MockServerHttpResponse response = new MockServerHttpResponse();
		when(exchange.getResponse()).thenReturn(response);
		ServerResponse.Context context = mock(ServerResponse.Context.class);

		result.flatMap(res -> res.writeTo(exchange, context)).block();

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("MyValue", response.getHeaders().getFirst("MyKey"));
		StepVerifier.create(response.getBody()).expectComplete().verify();
	}
