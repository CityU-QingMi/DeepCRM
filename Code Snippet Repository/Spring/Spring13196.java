	@Test
	public void sendEventWithTwoDataLines() throws Exception {
		this.emitter.send(event().data("foo").data("bar"));
		this.handler.assertSentObjectCount(5);
		this.handler.assertObject(0, "data:", SseEmitter.TEXT_PLAIN);
		this.handler.assertObject(1, "foo");
		this.handler.assertObject(2, "\ndata:", SseEmitter.TEXT_PLAIN);
		this.handler.assertObject(3, "bar");
		this.handler.assertObject(4, "\n\n", SseEmitter.TEXT_PLAIN);
	}
