	@Test
	public void shouldReceiveJsonAsResponseEntityString() throws Exception {
		String content = "{\"bar\":\"barbar\",\"foo\":\"foofoo\"}";
		prepareResponse(response -> response
				.setHeader("Content-Type", "application/json").setBody(content));

		Mono<ResponseEntity<String>> result = this.webClient.get()
				.uri("/json").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(response -> response.toEntity(String.class));

		StepVerifier.create(result)
				.consumeNextWith(entity -> {
					assertEquals(HttpStatus.OK, entity.getStatusCode());
					assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
					assertEquals(31, entity.getHeaders().getContentLength());
					assertEquals(content, entity.getBody());
				})
				.expectComplete().verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("/json", request.getPath());
			assertEquals("application/json", request.getHeader(HttpHeaders.ACCEPT));
		});
	}
