	@Test
	public void negate() throws Exception {
		RequestPredicate predicate = request -> false;
		RequestPredicate negated = predicate.negate();

		MockServerRequest mockRequest = MockServerRequest.builder().build();
		assertTrue(negated.test(mockRequest));

		predicate = request -> true;
		negated = predicate.negate();

		assertFalse(negated.test(mockRequest));
	}
