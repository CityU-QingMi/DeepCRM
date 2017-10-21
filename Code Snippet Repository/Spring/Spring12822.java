	@Test
	public void matchPatternsCondition() {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/foo");

		RequestMappingInfo info = paths("/foo*", "/bar").build();
		RequestMappingInfo expected = paths("/foo*").build();

		assertEquals(expected, info.getMatchingCondition(request));

		info = paths("/**", "/foo*", "/foo").build();
		expected = paths("/foo", "/foo*", "/**").build();

		assertEquals(expected, info.getMatchingCondition(request));
	}
