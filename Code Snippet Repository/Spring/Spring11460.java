	@Test
	public void shouldSendPojoAsJson() throws Exception {
		prepareResponse(response -> response.setHeader("Content-Type", "application/json")
				.setBody("{\"bar\":\"BARBAR\",\"foo\":\"FOOFOO\"}"));

		Mono<Pojo> result = this.webClient.post()
				.uri("/pojo/capitalize")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(new Pojo("foofoo", "barbar"))
				.exchange()
				.flatMap(response -> response.bodyToMono(Pojo.class));

		StepVerifier.create(result)
				.consumeNextWith(p -> assertEquals("BARBAR", p.getBar()))
				.expectComplete()
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("/pojo/capitalize", request.getPath());
			assertEquals("{\"foo\":\"foofoo\",\"bar\":\"barbar\"}", request.getBody().readUtf8());
			assertEquals("chunked", request.getHeader(HttpHeaders.TRANSFER_ENCODING));
			assertEquals("application/json", request.getHeader(HttpHeaders.ACCEPT));
			assertEquals("application/json", request.getHeader(HttpHeaders.CONTENT_TYPE));
		});
	}
