	@Test
	public void method() throws Exception {
		HttpMethod httpMethod = HttpMethod.GET;
		RequestPredicate predicate = RequestPredicates.method(httpMethod);
		MockServerRequest request = MockServerRequest.builder().method(httpMethod).build();
		assertTrue(predicate.test(request));

		request = MockServerRequest.builder().method(HttpMethod.POST).build();
		assertFalse(predicate.test(request));
	}
