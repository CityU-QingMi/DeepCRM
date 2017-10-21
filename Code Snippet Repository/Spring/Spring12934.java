	@Test
	public void writeServerSentEventsWithBuilder() throws Exception {

		ResolvableType type = ResolvableType.forClassWithGenerics(ServerSentEvent.class, String.class);

		EmitterProcessor<ServerSentEvent<?>> processor = EmitterProcessor.create();
		SseEmitter sseEmitter = (SseEmitter) handleValue(processor, Flux.class, type);

		EmitterHandler emitterHandler = new EmitterHandler();
		sseEmitter.initialize(emitterHandler);

		processor.onNext(ServerSentEvent.builder("foo").id("1").build());
		processor.onNext(ServerSentEvent.builder("bar").id("2").build());
		processor.onNext(ServerSentEvent.builder("baz").id("3").build());
		processor.onComplete();

		assertEquals("id:1\ndata:foo\n\nid:2\ndata:bar\n\nid:3\ndata:baz\n\n",
				emitterHandler.getValuesAsText());
	}
