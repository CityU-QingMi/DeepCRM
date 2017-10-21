	@Test
	public void matchCustomCondition() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.setParameter("foo", "bar");

		RequestMappingInfo info = paths("/foo").params("foo=bar").build();
		RequestMappingInfo match = info.getMatchingCondition(request);

		assertNotNull(match);

		info = paths("/foo").params("foo!=bar").params("foo!=bar").build();
		match = info.getMatchingCondition(request);

		assertNull(match);
	}
