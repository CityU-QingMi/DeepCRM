	@Test
	@SuppressWarnings("")
	public void doesNotSupport() throws NoSuchMethodException {

		Object value = null;

		MethodParameter returnType = on(TestController.class).resolveReturnType(String.class);
		assertFalse(this.resultHandler.supports(handlerResult(value, returnType)));

		returnType = on(TestController.class).resolveReturnType(Completable.class);
		assertFalse(this.resultHandler.supports(handlerResult(value, returnType)));

		// SPR-15464
		returnType = on(TestController.class).resolveReturnType(Flux.class);
		assertFalse(this.resultHandler.supports(handlerResult(value, returnType)));
	}
