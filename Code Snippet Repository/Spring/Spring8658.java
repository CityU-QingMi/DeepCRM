	@Test
	public void entityStream() throws Exception {

		FluxExchangeResult<Person> result = this.client.get()
				.accept(TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(TEXT_EVENT_STREAM)
				.returnResult(Person.class);

		StepVerifier.create(result.getResponseBody())
				.expectNext(new Person("N0"), new Person("N1"), new Person("N2"))
				.expectNextCount(4)
				.consumeNextWith(person -> assertThat(person.getName(), endsWith("7")))
				.thenCancel()
				.verify();
	}
