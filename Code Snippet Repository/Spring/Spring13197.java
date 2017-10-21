	@Test
	public void sendEventFullWithTwoDataLinesInTheMiddle() throws Exception {
		this.emitter.send(event().comment("blah").data("foo").data("bar").name("test").reconnectTime(5000L).id("1"));
		this.handler.assertSentObjectCount(5);
		this.handler.assertObject(0, ":blah\ndata:", SseEmitter.TEXT_PLAIN);
		this.handler.assertObject(1, "foo");
		this.handler.assertObject(2, "\ndata:", SseEmitter.TEXT_PLAIN);
		this.handler.assertObject(3, "bar");
		this.handler.assertObject(4, "\nevent:test\nretry:5000\nid:1\n\n", SseEmitter.TEXT_PLAIN);
	}
