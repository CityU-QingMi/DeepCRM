	@Test
	public void matchConsumesCondition() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.setContentType("text/plain");

		RequestMappingInfo info = paths("/foo").consumes("text/plain").build();
		RequestMappingInfo match = info.getMatchingCondition(request);

		assertNotNull(match);

		info = paths("/foo").consumes("application/xml").build();
		match = info.getMatchingCondition(request);

		assertNull(match);
	}
