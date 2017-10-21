	@Test
	public void shouldReceivePlainText() throws Exception {
		prepareResponse(response -> response.setBody("Hello Spring!"));

		Mono<String> result = this.webClient.get()
				.uri("/greeting?name=Spring")
				.header("X-Test-Header", "testvalue")
				.exchange()
				.flatMap(response -> response.bodyToMono(String.class));

		StepVerifier.create(result)
				.expectNext("Hello Spring!")
				.expectComplete().verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("testvalue", request.getHeader("X-Test-Header"));
			assertEquals("*/*", request.getHeader(HttpHeaders.ACCEPT));
			assertEquals("/greeting?name=Spring", request.getPath());
		});
	}
