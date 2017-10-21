	@Test
	public void pathPredicates() throws Exception {
		PathPatternParser parser = new PathPatternParser();
		parser.setCaseSensitive(false);
		Function<String, RequestPredicate> pathPredicates = RequestPredicates.pathPredicates(parser);

		URI uri = URI.create("http://localhost/path");
		RequestPredicate predicate = pathPredicates.apply("/P*");
		MockServerRequest request = MockServerRequest.builder().uri(uri).build();
		assertTrue(predicate.test(request));
	}
