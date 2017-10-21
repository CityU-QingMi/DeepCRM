	@Test
	public void preFlightRequestWithoutCorsEnabled() throws Exception {
		try {
			this.headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
			performOptions("/welcome", this.headers, String.class);
			fail();
		}
		catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
		}
	}
