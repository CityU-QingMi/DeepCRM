	@Test
	public void responseEntityFlux() throws Exception {

		EmitterProcessor<String> processor = EmitterProcessor.create();
		ResponseEntity<Flux<String>> entity = ResponseEntity.ok().body(processor);
		ResolvableType bodyType = forClassWithGenerics(Flux.class, String.class);
		MethodParameter type = on(TestController.class).resolveReturnType(ResponseEntity.class, bodyType);
		this.handler.handleReturnValue(entity, type, this.mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertEquals(200, this.response.getStatus());
		assertEquals("text/plain", this.response.getContentType());

		processor.onNext("foo");
		processor.onNext("bar");
		processor.onNext("baz");
		processor.onComplete();

		assertEquals("foobarbaz", this.response.getContentAsString());
	}
