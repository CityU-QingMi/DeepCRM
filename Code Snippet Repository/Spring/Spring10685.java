	@Test
	public void isNotPreFlightRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		assertFalse(CorsUtils.isPreFlightRequest(request));

		request = new MockHttpServletRequest();
		request.setMethod(HttpMethod.OPTIONS.name());
		request.addHeader(HttpHeaders.ORIGIN, "http://domain.com");
		assertFalse(CorsUtils.isPreFlightRequest(request));

		request = new MockHttpServletRequest();
		request.setMethod(HttpMethod.OPTIONS.name());
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		assertFalse(CorsUtils.isPreFlightRequest(request));
	}
