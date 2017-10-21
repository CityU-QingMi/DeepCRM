	@Test
	public void compareToMultipleExpressionsAndMultipeAcceptHeaderValues() {
		ProducesRequestCondition condition1 = new ProducesRequestCondition("text/*", "text/plain");
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/*", "application/xml");

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "text/plain");
		request.addHeader("Accept", "application/xml");

		int result = condition1.compareTo(condition2, request);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = condition2.compareTo(condition1, request);
		assertTrue("Invalid comparison result: " + result, result > 0);

		request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/xml");
		request.addHeader("Accept", "text/plain");

		result = condition1.compareTo(condition2, request);
		assertTrue("Invalid comparison result: " + result, result > 0);

		result = condition2.compareTo(condition1, request);
		assertTrue("Invalid comparison result: " + result, result < 0);
	}
