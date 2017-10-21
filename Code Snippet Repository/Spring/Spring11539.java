	@Test
	public void headers() throws Exception {
		String name = "MyHeader";
		String value = "MyValue";
		RequestPredicate predicate =
				RequestPredicates.headers(
						headers -> headers.header(name).equals(Collections.singletonList(value)));
		MockServerRequest request = MockServerRequest.builder().header(name, value).build();
		assertTrue(predicate.test(request));

		request = MockServerRequest.builder().build();
		assertFalse(predicate.test(request));
	}
