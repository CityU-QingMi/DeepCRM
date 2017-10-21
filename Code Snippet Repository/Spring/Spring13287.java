	@Test
	public void getStaticResourceUrlRequestWithQueryOrHash() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/");
		request.setRequestURI("/");

		String url = "/resources/foo.css?foo=bar&url=http://example.org";
		String resolvedUrl = this.urlProvider.getForRequestUrl(request, url);
		assertEquals("/resources/foo.css?foo=bar&url=http://example.org", resolvedUrl);

		url = "/resources/foo.css#hash";
		resolvedUrl = this.urlProvider.getForRequestUrl(request, url);
		assertEquals("/resources/foo.css#hash", resolvedUrl);
	}
