	@Test
	public void writeString() {
		Flux<String> source = Flux.just("foo", "bar");
		MockServerHttpResponse outputMessage = new MockServerHttpResponse();
		testWrite(source, outputMessage, String.class);

		StepVerifier.create(outputMessage.getBodyAsString())
				.expectNext("data:foo\n\ndata:bar\n\n")
				.expectComplete()
				.verify();
	}
