	@Test
	public void path() throws Exception {
		URI uri = URI.create("http://localhost/path");
		RequestPredicate predicate = RequestPredicates.path("/p*");
		MockServerRequest request = MockServerRequest.builder().uri(uri).build();
		assertTrue(predicate.test(request));

		request = MockServerRequest.builder().build();
		assertFalse(predicate.test(request));
	}
