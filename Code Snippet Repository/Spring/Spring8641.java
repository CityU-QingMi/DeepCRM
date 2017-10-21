	@Test
	public void sameSessionInstanceAfterMutate() throws Exception {

		WebHandler webHandler = exchange -> {
			if (exchange.getRequest().getURI().getPath().equals("/set")) {
				return exchange.getSession()
						.doOnNext(session -> session.getAttributes().put("foo", "bar"))
						.then();
			}
			else {
				return exchange.getSession()
						.map(session -> session.getAttributeOrDefault("foo", "none"))
						.flatMap(value -> {
							byte[] bytes = value.getBytes(UTF_8);
							DataBuffer buffer = new DefaultDataBufferFactory().wrap(bytes);
							return exchange.getResponse().writeWith(Mono.just(buffer));
						});
			}
		};

		WebTestClient client = new DefaultMockServerSpec(webHandler).configureClient().build();

		// Set the session attribute
		EntityExchangeResult<Void> result = client.get().uri("/set").exchange()
				.expectStatus().isOk().expectBody().isEmpty();

		ResponseCookie session = result.getResponseCookies().getFirst("SESSION");

		// Now get attribute
		client.mutate().build()
				.get().uri("/get")
				.cookie(session.getName(), session.getValue())
				.exchange()
				.expectBody(String.class).isEqualTo("bar");
	}
