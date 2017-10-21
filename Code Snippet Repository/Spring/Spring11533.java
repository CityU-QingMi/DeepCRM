	@Test
	public void or() throws Exception {
		RequestPredicate predicate1 = request -> true;
		RequestPredicate predicate2 = request -> false;
		RequestPredicate predicate3 = request -> false;

		MockServerRequest request = MockServerRequest.builder().build();
		assertTrue(predicate1.or(predicate2).test(request));
		assertTrue(predicate2.or(predicate1).test(request));
		assertFalse(predicate2.or(predicate3).test(request));
	}
