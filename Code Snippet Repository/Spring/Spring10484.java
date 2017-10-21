	@Test
	public void exchangePostCallback() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("MyHeader", "MyValue");
		requestHeaders.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld, requestHeaders);
		ListenableFuture<ResponseEntity<Void>> resultFuture =
				template.exchange(baseUrl + "/{method}", HttpMethod.POST, requestEntity, Void.class, "post");
		final URI expected =new URI(baseUrl + "/post/1");
		resultFuture.addCallback(new ListenableFutureCallback<ResponseEntity<Void>>() {
			@Override
			public void onSuccess(ResponseEntity<Void> result) {
				assertEquals("Invalid location", expected, result.getHeaders().getLocation());
				assertFalse(result.hasBody());
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(resultFuture);
	}
