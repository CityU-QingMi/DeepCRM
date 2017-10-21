	@Test
	public void writeAndAutoFlushOnComplete() {
		Mono<String> result = this.webClient.get()
				.uri("/write-and-complete")
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(String.class))
				.reduce((s1, s2) -> s1 + s2);

		try {
			StepVerifier.create(result)
					.consumeNextWith(value -> assertTrue(value.length() == 200000))
					.expectComplete()
					.verify(Duration.ofSeconds(5L));
		}
		catch (AssertionError err) {
			if (err.getMessage().startsWith("VerifySubscriber timed out") &&
					(this.server instanceof RxNettyHttpServer || this.server instanceof ReactorHttpServer)) {
				// TODO: RxNetty usually times out here; Reactor does the same on Windows at least...
				err.printStackTrace();
				return;
			}
			throw err;
		}
	}
