	@Test
	public void compareToEqualMatch() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "text/*");

		ProducesRequestCondition condition1 = new ProducesRequestCondition("text/plain");
		ProducesRequestCondition condition2 = new ProducesRequestCondition("text/xhtml");

		int result = condition1.compareTo(condition2, request);
		assertTrue("Should have used MediaType.equals(Object) to break the match", result < 0);

		result = condition2.compareTo(condition1, request);
		assertTrue("Should have used MediaType.equals(Object) to break the match", result > 0);
	}
