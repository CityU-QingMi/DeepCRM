	@Test
	public void postForEntityCallback() throws Exception  {
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld);
		ListenableFuture<ResponseEntity<String>> responseEntityFuture =
				template.postForEntity(baseUrl + "/{method}", requestEntity, String.class, "post");
		responseEntityFuture.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onSuccess(ResponseEntity<String> result) {
				assertEquals("Invalid content", helloWorld, result.getBody());
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(responseEntityFuture);
	}
