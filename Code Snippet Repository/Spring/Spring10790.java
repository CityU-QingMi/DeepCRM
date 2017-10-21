	@Test
	public void isEligibleForEtag() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		assertTrue(filter.isEligibleForEtag(request, response, 200, StreamUtils.emptyInput()));
		assertFalse(filter.isEligibleForEtag(request, response, 300, StreamUtils.emptyInput()));

		request = new MockHttpServletRequest("HEAD", "/hotels");
		assertFalse(filter.isEligibleForEtag(request, response, 200, StreamUtils.emptyInput()));

		request = new MockHttpServletRequest("POST", "/hotels");
		assertFalse(filter.isEligibleForEtag(request, response, 200, StreamUtils.emptyInput()));

		request = new MockHttpServletRequest("POST", "/hotels");
		request.addHeader("Cache-Control","must-revalidate, no-store");
		assertFalse(filter.isEligibleForEtag(request, response, 200, StreamUtils.emptyInput()));
	}
