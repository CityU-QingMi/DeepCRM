	@Test
	public void emptyBodyWithCompletableFuture() throws Exception {
		ResolvableType type = httpEntityType(CompletableFuture.class, String.class);
		HttpEntity<CompletableFuture<String>> entity = resolveValueWithEmptyBody(type);

		entity.getBody().whenComplete((body, ex) -> {
			assertNull(body);
			assertNull(ex);
		});
	}
