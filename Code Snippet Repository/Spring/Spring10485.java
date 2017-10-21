	@Test
	public void exchangePostCallbackWithLambdas() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("MyHeader", "MyValue");
		requestHeaders.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld, requestHeaders);
		ListenableFuture<ResponseEntity<Void>> resultFuture =
				template.exchange(baseUrl + "/{method}", HttpMethod.POST, requestEntity, Void.class, "post");
		final URI expected =new URI(baseUrl + "/post/1");
		resultFuture.addCallback(result -> {
			assertEquals("Invalid location", expected, result.getHeaders().getLocation());
			assertFalse(result.hasBody());
			}, ex -> fail(ex.getMessage()));
		waitTillDone(resultFuture);
	}
