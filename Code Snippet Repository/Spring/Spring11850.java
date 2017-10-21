	@Test
	@SuppressWarnings("")
	public void supports() throws NoSuchMethodException {

		Object value = null;

		MethodParameter returnType = on(TestController.class).resolveReturnType(entity(String.class));
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));

		returnType = on(TestController.class).resolveReturnType(Mono.class, entity(String.class));
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));

		returnType = on(TestController.class).resolveReturnType(Single.class, entity(String.class));
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));

		returnType = on(TestController.class).resolveReturnType(CompletableFuture.class, entity(String.class));
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));

		returnType = on(TestController.class).resolveReturnType(HttpHeaders.class);
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));

		// SPR-15785
		value = ResponseEntity.ok("testing");
		returnType = on(TestController.class).resolveReturnType(Object.class);
		assertTrue(this.resultHandler.supports(handlerResult(value, returnType)));
	}
