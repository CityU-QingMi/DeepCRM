	@Test
	public void isNotPreFlightRequest() {
		MockServerHttpRequest request = get("/").build();
		assertFalse(CorsUtils.isPreFlightRequest(request));

		request = options("/").header(HttpHeaders.ORIGIN, "http://domain.com").build();
		assertFalse(CorsUtils.isPreFlightRequest(request));

		request = options("/").header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET").build();
		assertFalse(CorsUtils.isPreFlightRequest(request));
	}
