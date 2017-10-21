	@Test
	public void shouldReceiveJsonAsPojo() throws Exception {
		prepareResponse(response -> response
				.setHeader("Content-Type", "application/json")
				.setBody("{\"bar\":\"barbar\",\"foo\":\"foofoo\"}"));

		Mono<Pojo> result = this.webClient.get()
				.uri("/pojo")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(response -> response.bodyToMono(Pojo.class));

		StepVerifier.create(result)
				.consumeNextWith(p -> assertEquals("barbar", p.getBar()))
				.expectComplete()
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("/pojo", request.getPath());
			assertEquals("application/json", request.getHeader(HttpHeaders.ACCEPT));
		});
	}
