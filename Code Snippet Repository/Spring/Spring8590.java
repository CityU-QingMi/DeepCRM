	@Test
	public void isSatisfied() throws Exception {
		RequestExpectation expectation = new DefaultRequestExpectation(twice(), requestTo("/foo"));
		expectation.andRespond(withSuccess());

		expectation.createResponse(createRequest(GET, "/foo"));
		assertFalse(expectation.isSatisfied());

		expectation.createResponse(createRequest(GET, "/foo"));
		assertTrue(expectation.isSatisfied());
	}
