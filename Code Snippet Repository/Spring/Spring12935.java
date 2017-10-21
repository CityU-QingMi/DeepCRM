	@Test
	public void writeStreamJson() throws Exception {

		this.servletRequest.addHeader("Accept", "application/stream+json");

		EmitterProcessor<Bar> processor = EmitterProcessor.create();
		ResponseBodyEmitter emitter = handleValue(processor, Flux.class, forClass(Bar.class));

		EmitterHandler emitterHandler = new EmitterHandler();
		emitter.initialize(emitterHandler);

		ServletServerHttpResponse message = new ServletServerHttpResponse(this.servletResponse);
		emitter.extendResponse(message);

		Bar bar1 = new Bar("foo");
		Bar bar2 = new Bar("bar");

		processor.onNext(bar1);
		processor.onNext(bar2);
		processor.onComplete();

		assertEquals("application/stream+json", message.getHeaders().getContentType().toString());
		assertEquals(Arrays.asList(bar1, "\n", bar2, "\n"), emitterHandler.getValues());
	}
