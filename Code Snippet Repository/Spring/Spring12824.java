	@Test
	public void matchHeadersCondition() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.addHeader("foo", "bar");

		RequestMappingInfo info = paths("/foo").headers("foo=bar").build();
		RequestMappingInfo match = info.getMatchingCondition(request);

		assertNotNull(match);

		info = paths("/foo").headers("foo!=bar").build();
		match = info.getMatchingCondition(request);

		assertNull(match);
	}
