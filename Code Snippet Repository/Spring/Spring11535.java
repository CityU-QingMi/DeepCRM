	@Test
	public void methods() throws Exception {
		URI uri = URI.create("http://localhost/path");

		RequestPredicate predicate = RequestPredicates.GET("/p*");
		MockServerRequest request = MockServerRequest.builder().method(HttpMethod.GET).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.HEAD("/p*");
		request = MockServerRequest.builder().method(HttpMethod.HEAD).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.POST("/p*");
		request = MockServerRequest.builder().method(HttpMethod.POST).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.PUT("/p*");
		request = MockServerRequest.builder().method(HttpMethod.PUT).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.PATCH("/p*");
		request = MockServerRequest.builder().method(HttpMethod.PATCH).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.DELETE("/p*");
		request = MockServerRequest.builder().method(HttpMethod.DELETE).uri(uri).build();
		assertTrue(predicate.test(request));

		predicate = RequestPredicates.OPTIONS("/p*");
		request = MockServerRequest.builder().method(HttpMethod.OPTIONS).uri(uri).build();
		assertTrue(predicate.test(request));
	}
