	@Test
	public void statusCallback() throws Exception {
		URI uri = new URI(baseUrl + "/status/notfound");
		AsyncClientHttpRequest request = this.factory.createAsyncRequest(uri, HttpMethod.GET);
		assertEquals("Invalid HTTP method", HttpMethod.GET, request.getMethod());
		assertEquals("Invalid HTTP URI", uri, request.getURI());
		ListenableFuture<ClientHttpResponse> listenableFuture = request.executeAsync();
		listenableFuture.addCallback(new ListenableFutureCallback<ClientHttpResponse>() {
			@Override
			public void onSuccess(ClientHttpResponse result) {
				try {
					assertEquals("Invalid status code", HttpStatus.NOT_FOUND, result.getStatusCode());
				}
				catch (IOException ex) {
					fail(ex.getMessage());
				}
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		ClientHttpResponse response = listenableFuture.get();
		try {
			assertEquals("Invalid status code", HttpStatus.NOT_FOUND, response.getStatusCode());
		}
		finally {
			response.close();
		}
	}
