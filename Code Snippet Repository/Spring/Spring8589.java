	@Test
	public void hasRemainingCount() throws Exception {
		RequestExpectation expectation = new DefaultRequestExpectation(twice(), requestTo("/foo"));
		expectation.andRespond(withSuccess());

		expectation.createResponse(createRequest(GET, "/foo"));
		assertTrue(expectation.hasRemainingCount());

		expectation.createResponse(createRequest(GET, "/foo"));
		assertFalse(expectation.hasRemainingCount());
	}
