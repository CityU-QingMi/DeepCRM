	@Test
	public void getPathWithinServletWithoutUrlDecoding() {
		request.setContextPath("/SPR-11101");
		request.setServletPath("/test_url_decoding/a/b");
		request.setRequestURI("/test_url_decoding/a%2Fb");

		helper.setUrlDecode(false);
		String actual = helper.getPathWithinServletMapping(request);

		assertEquals("/test_url_decoding/a%2Fb", actual);
	}
