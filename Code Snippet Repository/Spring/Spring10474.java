	@Test
	public void notFoundCallbackWithLambdas() throws Exception {
		ListenableFuture<?> future = template.execute(baseUrl + "/status/notfound", HttpMethod.GET, null, null);
		future.addCallback(result -> fail("onSuccess not expected"), ex -> {
				assertTrue(ex instanceof HttpClientErrorException);
				HttpClientErrorException hcex = (HttpClientErrorException) ex;
				assertEquals(HttpStatus.NOT_FOUND, hcex.getStatusCode());
				assertNotNull(hcex.getStatusText());
				assertNotNull(hcex.getResponseBodyAsString());
		});
		waitTillDone(future);
	}
