	@Test
	public void getMatchingConditionWithCorsPreFlight() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "");
		request.addHeader("Origin", "http://example.com");
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "PUT");

		assertNotNull(new RequestMethodsRequestCondition().getMatchingCondition(request));
		assertNotNull(new RequestMethodsRequestCondition(PUT).getMatchingCondition(request));
		assertNull(new RequestMethodsRequestCondition(DELETE).getMatchingCondition(request));
	}
