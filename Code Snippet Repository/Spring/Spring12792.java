	@Test
	public void compareToMediaTypeAllWithParameter() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "*/*;q=0.9");

		ProducesRequestCondition condition1 = new ProducesRequestCondition();
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, request) < 0);
		assertTrue(condition2.compareTo(condition1, request) > 0);
	}
