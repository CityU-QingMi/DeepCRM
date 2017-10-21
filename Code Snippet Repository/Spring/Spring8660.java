	@Test
	public void testWebFilter() throws Exception {

		WebFilter filter = (exchange, chain) -> {
			DataBuffer buffer = new DefaultDataBufferFactory().allocateBuffer();
			buffer.write("It works!".getBytes(StandardCharsets.UTF_8));
			return exchange.getResponse().writeWith(Mono.just(buffer));
		};

		WebTestClient client = WebTestClient.bindToWebHandler(exchange -> Mono.empty())
				.webFilter(filter)
				.build();

		client.get().uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("It works!");
	}
