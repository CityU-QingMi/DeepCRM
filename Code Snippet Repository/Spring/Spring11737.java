	@Test
	public void jsonStreaming() throws Exception {
		Flux<Person> result = this.webClient.get()
				.uri("/stream")
				.accept(APPLICATION_STREAM_JSON)
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(Person.class));

		StepVerifier.create(result)
				.expectNext(new Person("foo 0"))
				.expectNext(new Person("foo 1"))
				.thenCancel()
				.verify();
	}
