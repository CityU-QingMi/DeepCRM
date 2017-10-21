	@Test
	public void emptyBodyWithCompletableFuture() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(CompletableFuture.class, String.class);
		CompletableFuture<String> future = resolveValueWithEmptyBody(param);
		future.whenComplete((text, ex) -> {
			assertNull(text);
			assertNotNull(ex);
		});

		param = this.testMethod.annot(requestBody().notRequired()).arg(CompletableFuture.class, String.class);
		future = resolveValueWithEmptyBody(param);
		future.whenComplete((text, ex) -> {
			assertNotNull(text);
			assertNull(ex);
		});
	}
