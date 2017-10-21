	@Test
	public void postForLocationCallback() throws Exception  {
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.ISO_8859_1));
		HttpEntity<String> entity = new HttpEntity<>(helloWorld, entityHeaders);
		final URI expected = new URI(baseUrl + "/post/1");
		ListenableFuture<URI> locationFuture = template.postForLocation(baseUrl + "/{method}", entity, "post");
		locationFuture.addCallback(new ListenableFutureCallback<URI>() {
			@Override
			public void onSuccess(URI result) {
				assertEquals("Invalid location", expected, result);
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(locationFuture);
	}
