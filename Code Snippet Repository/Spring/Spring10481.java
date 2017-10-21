	@Test
	public void getEntityCallbackWithLambdas() throws Exception {
		ListenableFuture<ResponseEntity<String>> futureEntity =
				template.getForEntity(baseUrl + "/{method}", String.class, "get");
		futureEntity.addCallback((entity) -> {
			assertEquals("Invalid content", helloWorld, entity.getBody());
			assertFalse("No headers", entity.getHeaders().isEmpty());
			assertEquals("Invalid content-type", textContentType, entity.getHeaders().getContentType());
			assertEquals("Invalid status code", HttpStatus.OK, entity.getStatusCode());
		}, ex -> fail(ex.getMessage()));
		waitTillDone(futureEntity);
	}
