	@Test
	public void supportsReturnTypes() throws Exception {

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(ResponseBodyEmitter.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(SseEmitter.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(ResponseEntity.class, ResponseBodyEmitter.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(Flux.class, String.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(forClassWithGenerics(ResponseEntity.class,
								forClassWithGenerics(Flux.class, String.class)))));
	}
