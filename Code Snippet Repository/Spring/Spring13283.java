	@Test
	public void encodeURLWithRequestParams() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.setContextPath("/");
		request.setAttribute(ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR, this.resourceUrlProvider);
		MockHttpServletResponse response = new MockHttpServletResponse();

		this.filter.doFilter(request, response, (req, res) -> {
			String result = ((HttpServletResponse) res).encodeURL("/resources/bar.css?foo=bar&url=http://example.org");
			assertEquals("/resources/bar-11e16cf79faee7ac698c805cf28248d2.css?foo=bar&url=http://example.org", result);
		});
	}
