	@Test
	public void notFoundCallback() throws Exception {
		ListenableFuture<?> future = template.execute(baseUrl + "/status/notfound", HttpMethod.GET, null, null);
		future.addCallback(new ListenableFutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				fail("onSuccess not expected");
			}
			@Override
			public void onFailure(Throwable t) {
				assertTrue(t instanceof HttpClientErrorException);
				HttpClientErrorException ex = (HttpClientErrorException) t;
				assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
				assertNotNull(ex.getStatusText());
				assertNotNull(ex.getResponseBodyAsString());
			}
		});
		waitTillDone(future);
	}
