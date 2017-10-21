	@Test
	public void doesNotSupportReturnTypes() throws Exception {

		assertFalse(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(ResponseEntity.class, String.class)));

		assertFalse(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(forClassWithGenerics(ResponseEntity.class,
						forClassWithGenerics(AtomicReference.class, String.class)))));

		assertFalse(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(ResponseEntity.class)));
	}
