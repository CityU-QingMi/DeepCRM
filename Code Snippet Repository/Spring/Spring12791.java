	@Test
	public void compareToMediaTypeAll() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		ProducesRequestCondition condition1 = new ProducesRequestCondition();
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/json");

		assertTrue("Should have picked '*/*' condition as an exact match",
				condition1.compareTo(condition2, request) < 0);
		assertTrue("Should have picked '*/*' condition as an exact match",
				condition2.compareTo(condition1, request) > 0);

		condition1 = new ProducesRequestCondition("*/*");
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, request) < 0);
		assertTrue(condition2.compareTo(condition1, request) > 0);

		request.addHeader("Accept", "*/*");

		condition1 = new ProducesRequestCondition();
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, request) < 0);
		assertTrue(condition2.compareTo(condition1, request) > 0);

		condition1 = new ProducesRequestCondition("*/*");
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, request) < 0);
		assertTrue(condition2.compareTo(condition1, request) > 0);
	}
