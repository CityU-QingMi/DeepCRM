	@Test
	public void writeServerSentEvents() throws Exception {

		this.servletRequest.addHeader("Accept", "text/event-stream");
		EmitterProcessor<String> processor = EmitterProcessor.create();
		SseEmitter sseEmitter = (SseEmitter) handleValue(processor, Flux.class, forClass(String.class));

		EmitterHandler emitterHandler = new EmitterHandler();
		sseEmitter.initialize(emitterHandler);

		processor.onNext("foo");
		processor.onNext("bar");
		processor.onNext("baz");
		processor.onComplete();

		assertEquals("data:foo\n\ndata:bar\n\ndata:baz\n\n", emitterHandler.getValuesAsText());
	}
