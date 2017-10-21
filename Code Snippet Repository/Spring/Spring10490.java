	@Test
	public void headForHeadersCallback() throws Exception {
		ListenableFuture<HttpHeaders> headersFuture = template.headForHeaders(baseUrl + "/get");
		headersFuture.addCallback(new ListenableFutureCallback<HttpHeaders>() {
			@Override
			public void onSuccess(HttpHeaders result) {
				assertTrue("No Content-Type header", result.containsKey("Content-Type"));
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(headersFuture);
	}
