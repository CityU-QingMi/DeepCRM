	@Test
	@SuppressWarnings({ "", "" })
	public void exchangeGetCallbackWithLambdas() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("MyHeader", "MyValue");
		HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
		ListenableFuture<ResponseEntity<String>> responseFuture =
				template.exchange(baseUrl + "/{method}", HttpMethod.GET, requestEntity, String.class, "get");
		responseFuture.addCallback(result -> assertEquals("Invalid content", helloWorld,
				result.getBody()), ex -> fail(ex.getMessage()));
		waitTillDone(responseFuture);
	}
