	@Test
	public void getEntityCallback() throws Exception {
		ListenableFuture<ResponseEntity<String>> futureEntity =
				template.getForEntity(baseUrl + "/{method}", String.class, "get");
		futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onSuccess(ResponseEntity<String> entity) {
				assertEquals("Invalid content", helloWorld, entity.getBody());
				assertFalse("No headers", entity.getHeaders().isEmpty());
				assertEquals("Invalid content-type", textContentType, entity.getHeaders().getContentType());
				assertEquals("Invalid status code", HttpStatus.OK, entity.getStatusCode());
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(futureEntity);
	}
