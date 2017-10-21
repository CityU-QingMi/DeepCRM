	@Test
	public void writeServerSentEvent() {

		ServerSentEvent<?> event = ServerSentEvent.builder().data("bar").id("c42").event("foo")
				.comment("bla\nbla bla\nbla bla bla").retry(Duration.ofMillis(123L)).build();

		Mono<ServerSentEvent> source = Mono.just(event);
		MockServerHttpResponse outputMessage = new MockServerHttpResponse();
		testWrite(source, outputMessage, ServerSentEvent.class);

		StepVerifier.create(outputMessage.getBodyAsString())
				.expectNext("id:c42\nevent:foo\nretry:123\n:bla\n:bla bla\n:bla bla bla\ndata:bar\n\n")
				.expectComplete()
				.verify();
	}
