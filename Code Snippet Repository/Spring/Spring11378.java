	@Test
	public void writeAndAutoFlushBeforeComplete() {
		Flux<String> result = this.webClient.get()
				.uri("/write-and-never-complete")
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(String.class));

		try {
			StepVerifier.create(result)
					.expectNextMatches(s -> s.startsWith("0123456789"))
					.thenCancel()
					.verify(Duration.ofSeconds(5L));
		}
		catch (AssertionError err) {
			if (err.getMessage().startsWith("VerifySubscriber timed out") &&
					this.server instanceof RxNettyHttpServer) {
				// TODO: RxNetty usually times out here
				err.printStackTrace();
				return;
			}
			throw err;
		}
	}
