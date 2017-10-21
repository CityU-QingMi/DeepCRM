	@Test
	public void pathExtension() throws Exception {
		RequestPredicate predicate = RequestPredicates.pathExtension("txt");

		URI uri = URI.create("http://localhost/file.txt");
		MockServerRequest request = MockServerRequest.builder().uri(uri).build();
		assertTrue(predicate.test(request));

		uri = URI.create("http://localhost/FILE.TXT");
		request = MockServerRequest.builder().uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.pathExtension("bar");
		assertFalse(predicate.test(request));

		uri = URI.create("http://localhost/file.foo");
		request = MockServerRequest.builder().uri(uri).build();
		assertFalse(predicate.test(request));
	}
