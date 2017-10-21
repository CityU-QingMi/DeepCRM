	@Test
	public void serverErrorCallbackWithLambdas() throws Exception {
		ListenableFuture<Void> future = template.execute(baseUrl + "/status/server", HttpMethod.GET, null, null);
		future.addCallback(result -> fail("onSuccess not expected"), ex -> {
				assertTrue(ex instanceof HttpServerErrorException);
				HttpServerErrorException hsex = (HttpServerErrorException) ex;
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hsex.getStatusCode());
				assertNotNull(hsex.getStatusText());
				assertNotNull(hsex.getResponseBodyAsString());
		});
		waitTillDone(future);
	}
