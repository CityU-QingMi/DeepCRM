	@Test
	public void serverErrorCallback() throws Exception {
		ListenableFuture<Void> future = template.execute(baseUrl + "/status/server", HttpMethod.GET, null, null);
		future.addCallback(new ListenableFutureCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				fail("onSuccess not expected");
			}
			@Override
			public void onFailure(Throwable ex) {
				assertTrue(ex instanceof HttpServerErrorException);
				HttpServerErrorException hsex = (HttpServerErrorException) ex;
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hsex.getStatusCode());
				assertNotNull(hsex.getStatusText());
				assertNotNull(hsex.getResponseBodyAsString());
			}
		});
		waitTillDone(future);
	}
