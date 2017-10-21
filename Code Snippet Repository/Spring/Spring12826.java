	@Test
	public void matchProducesCondition() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.addHeader("Accept", "text/plain");

		RequestMappingInfo info = paths("/foo").produces("text/plain").build();
		RequestMappingInfo match = info.getMatchingCondition(request);

		assertNotNull(match);

		info = paths("/foo").produces("application/xml").build();
		match = info.getMatchingCondition(request);

		assertNull(match);
	}
