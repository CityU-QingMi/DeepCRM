	@Test
	public void readServerSentEventsWithMultipleChunks() {
		MockServerHttpRequest request = MockServerHttpRequest.post("/")
				.body(Flux.just(
						stringBuffer("id:c42\nev"),
						stringBuffer("ent:foo\nretry:123\n:bla\n:bla bla\n:bla bla bla\ndata:"),
						stringBuffer("bar\n\nid:c43\nevent:bar\nretry:456\ndata:baz\n\n")));

		Flux<ServerSentEvent> events = messageReader
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
