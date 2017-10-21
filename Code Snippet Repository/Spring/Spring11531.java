	@Test
	public void and() throws Exception {
		RequestPredicate predicate1 = request -> true;
		RequestPredicate predicate2 = request -> true;
		RequestPredicate predicate3 = request -> false;

		MockServerRequest request = MockServerRequest.builder().build();
		assertTrue(predicate1.and(predicate2).test(request));
		assertTrue(predicate2.and(predicate1).test(request));
		assertFalse(predicate1.and(predicate3).test(request));
	}
