	@Test
	public void readServerSentEvents() {
		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(
				"id:c42\nevent:foo\nretry:123\n:bla\n:bla bla\n:bla bla bla\ndata:bar\n\n" +
			 	"id:c43\nevent:bar\nretry:456\ndata:baz\n\n");

		Flux<ServerSentEvent> events = this.messageReader
				.read(ResolvableType.forClassWithGenerics(ServerSentEvent.class, String.class),
						request, Collections.emptyMap()).cast(ServerSentEvent.class);

		StepVerifier.create(events)
				.consumeNextWith(event -> {
					assertEquals("c42", event.id());
					assertEquals("foo", event.event());
					assertEquals(Duration.ofMillis(123), event.retry());
					assertEquals("bla\nbla bla\nbla bla bla", event.comment());
					assertEquals("bar", event.data());
				})
				.consumeNextWith(event -> {
					assertEquals("c43", event.id());
					assertEquals("bar", event.event());
					assertEquals(Duration.ofMillis(456), event.retry());
					assertNull(event.comment());
					assertEquals("baz", event.data());
				})
				.expectComplete()
				.verify();
	}
