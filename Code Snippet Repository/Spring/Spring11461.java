	@Test
	public void shouldSendCookies() throws Exception {
		prepareResponse(response -> response
				.setHeader("Content-Type", "text/plain").setBody("test"));

		Mono<String> result = this.webClient.get()
				.uri("/test")
				.cookie("testkey", "testvalue")
				.exchange()
				.flatMap(response -> response.bodyToMono(String.class));

		StepVerifier.create(result)
				.expectNext("test")
				.expectComplete()
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("/test", request.getPath());
			assertEquals("testkey=testvalue", request.getHeader(HttpHeaders.COOKIE));
		});
	}
