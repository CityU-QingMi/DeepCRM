	@Test
	public void putCallback() throws Exception  {
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld);
		ListenableFuture<?> responseEntityFuture = template.put(baseUrl + "/{method}", requestEntity, "put");
		responseEntityFuture.addCallback(new ListenableFutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				assertNull(result);
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(responseEntityFuture);
	}
