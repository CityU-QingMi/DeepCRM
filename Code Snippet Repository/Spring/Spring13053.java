	@Test
	public void responseBodyFlux() throws Exception {

		this.request.addHeader("Accept", "text/event-stream");

		MethodParameter type = on(TestController.class).resolveReturnType(Flux.class, String.class);
		EmitterProcessor<String> processor = EmitterProcessor.create();
		this.handler.handleReturnValue(processor, type, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals(200, this.response.getStatus());
		assertEquals("text/event-stream;charset=UTF-8", this.response.getContentType());

		processor.onNext("foo");
		processor.onNext("bar");
		processor.onNext("baz");
		processor.onComplete();

		assertEquals("data:foo\n\ndata:bar\n\ndata:baz\n\n", this.response.getContentAsString());
	}
