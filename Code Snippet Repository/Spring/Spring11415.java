	@Test
	public void ofObject() throws Exception {
		User body = new User("foo", "bar");
		BodyInserter<User, ReactiveHttpOutputMessage> inserter = BodyInserters.fromObject(body);
		MockServerHttpResponse response = new MockServerHttpResponse();
		Mono<Void> result = inserter.insert(response, this.context);
		StepVerifier.create(result).expectComplete().verify();

		StepVerifier.create(response.getBodyAsString())
				.expectNext("{\"username\":\"foo\",\"password\":\"bar\"}")
				.expectComplete()
				.verify();
	}
