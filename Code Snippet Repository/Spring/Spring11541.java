	@Test
	public void accept() throws Exception {
		MediaType json = MediaType.APPLICATION_JSON;
		RequestPredicate predicate = RequestPredicates.accept(json);
		MockServerRequest request = MockServerRequest.builder().header("Accept", json.toString()).build();
		assertTrue(predicate.test(request));

		request = MockServerRequest.builder().header("Accept", MediaType.TEXT_XML_VALUE).build();
		assertFalse(predicate.test(request));
	}
