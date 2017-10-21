	@Test
	public void optionsForAllowCallback() throws Exception {
		ListenableFuture<Set<HttpMethod>> allowedFuture = template.optionsForAllow(new URI(baseUrl + "/get"));
		allowedFuture.addCallback(new ListenableFutureCallback<Set<HttpMethod>>() {
			@Override
			public void onSuccess(Set<HttpMethod> result) {
				assertEquals("Invalid response", EnumSet.of(HttpMethod.GET, HttpMethod.OPTIONS,
						HttpMethod.HEAD, HttpMethod.TRACE), result);
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(allowedFuture);
	}
