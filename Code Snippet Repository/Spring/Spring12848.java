	@Test
	public void supportsReturnType() throws Exception {
		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(DeferredResult.class, String.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(ListenableFuture.class, String.class)));

		assertTrue(this.handler.supportsReturnType(
				on(TestController.class).resolveReturnType(CompletableFuture.class, String.class)));
	}
