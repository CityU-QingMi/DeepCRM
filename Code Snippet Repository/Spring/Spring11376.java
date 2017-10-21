	@Test
	public void writeAndFlushWith() throws Exception {
		Mono<String> result = this.webClient.get()
				.uri("/write-and-flush")
				.exchange()
				.flatMapMany(response -> response.body(BodyExtractors.toFlux(String.class)))
				.takeUntil(s -> s.endsWith("data1"))
				.reduce((s1, s2) -> s1 + s2);

		StepVerifier.create(result)
				.expectNext("data0data1")
				.expectComplete()
				.verify(Duration.ofSeconds(5L));
	}
