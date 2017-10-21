	@Test
	public void postForLocationCallbackWithLambdas() throws Exception  {
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.ISO_8859_1));
		HttpEntity<String> entity = new HttpEntity<>(helloWorld, entityHeaders);
		final URI expected = new URI(baseUrl + "/post/1");
		ListenableFuture<URI> locationFuture = template.postForLocation(baseUrl + "/{method}", entity, "post");
		locationFuture.addCallback(result -> assertEquals("Invalid location", expected, result),
				ex -> fail(ex.getMessage()));
		waitTillDone(locationFuture);
	}
