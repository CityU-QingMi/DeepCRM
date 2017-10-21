	@Test
	public void pathEncoded() throws Exception {
		URI uri = URI.create("http://localhost/foo%20bar");
		RequestPredicate predicate = RequestPredicates.path("/foo bar");
		MockServerRequest request = MockServerRequest.builder().uri(uri).build();
		assertTrue(predicate.test(request));

		request = MockServerRequest.builder().build();
		assertFalse(predicate.test(request));
	}
