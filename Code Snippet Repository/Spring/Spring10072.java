	@Test
	public void writeMultiLineString() {
		Flux<String> source = Flux.just("foo\nbar", "foo\nbaz");
		MockServerHttpResponse outputMessage = new MockServerHttpResponse();
		testWrite(source, outputMessage, String.class);

		StepVerifier.create(outputMessage.getBodyAsString())
				.expectNext("data:foo\ndata:bar\n\ndata:foo\ndata:baz\n\n")
				.expectComplete()
				.verify();
	}
