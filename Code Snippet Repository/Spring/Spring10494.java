	@Test
	public void postForEntityCallbackWithLambdas() throws Exception  {
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld);
		ListenableFuture<ResponseEntity<String>> responseEntityFuture =
				template.postForEntity(baseUrl + "/{method}", requestEntity, String.class, "post");
		responseEntityFuture.addCallback(
				result -> assertEquals("Invalid content", helloWorld, result.getBody()),
				ex -> fail(ex.getMessage()));
		waitTillDone(responseEntityFuture);
	}
