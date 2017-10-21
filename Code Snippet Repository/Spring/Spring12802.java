	@Test
	public void compareTo() {
		RequestMethodsRequestCondition c1 = new RequestMethodsRequestCondition(GET, HEAD);
		RequestMethodsRequestCondition c2 = new RequestMethodsRequestCondition(POST);
		RequestMethodsRequestCondition c3 = new RequestMethodsRequestCondition();

		MockHttpServletRequest request = new MockHttpServletRequest();

		int result = c1.compareTo(c2, request);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = c2.compareTo(c1, request);
		assertTrue("Invalid comparison result: " + result, result > 0);

		result = c2.compareTo(c3, request);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = c1.compareTo(c1, request);
		assertEquals("Invalid comparison result ", 0, result);
	}
