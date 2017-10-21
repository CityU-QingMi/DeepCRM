	@Test
	public void writePojo() {
		Flux<Pojo> source = Flux.just(new Pojo("foofoo", "barbar"), new Pojo("foofoofoo", "barbarbar"));
		MockServerHttpResponse outputMessage = new MockServerHttpResponse();
		testWrite(source, outputMessage, Pojo.class);

		StepVerifier.create(outputMessage.getBodyAsString())
				.expectNext("data:{\"foo\":\"foofoo\",\"bar\":\"barbar\"}\n\n" +
						"data:{\"foo\":\"foofoofoo\",\"bar\":\"barbarbar\"}\n\n")
				.expectComplete()
				.verify();
	}
