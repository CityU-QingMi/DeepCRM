	@Test
	public void preFlightRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/foo");
		request.addHeader(HttpHeaders.ORIGIN, "http://domain.com");
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "POST");

		RequestMappingInfo info = paths("/foo").methods(RequestMethod.POST).build();
		RequestMappingInfo match = info.getMatchingCondition(request);
		assertNotNull(match);

		info = paths("/foo").methods(RequestMethod.OPTIONS).build();
		match = info.getMatchingCondition(request);
		assertNull("Pre-flight should match the ACCESS_CONTROL_REQUEST_METHOD", match);
	}
