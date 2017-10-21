	@Test
	public void ofObjectWithHints() throws Exception {
		User body = new User("foo", "bar");
		BodyInserter<User, ReactiveHttpOutputMessage> inserter = BodyInserters.fromObject(body);
		this.hints.put(JSON_VIEW_HINT, SafeToSerialize.class);
		MockServerHttpResponse response = new MockServerHttpResponse();
		Mono<Void> result = inserter.insert(response, this.context);
		StepVerifier.create(result).expectComplete().verify();

		StepVerifier.create(response.getBodyAsString())
				.expectNext("{\"username\":\"foo\"}")
				.expectComplete()
				.verify();
	}
