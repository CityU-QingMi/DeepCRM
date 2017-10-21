	@Test
	public void encodeURL() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		request.setAttribute(ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR, this.resourceUrlProvider);
		MockHttpServletResponse response = new MockHttpServletResponse();

		this.filter.doFilter(request, response, (req, res) -> {
			String result = ((HttpServletResponse) res).encodeURL("/resources/bar.css");
			assertEquals("/resources/bar-11e16cf79faee7ac698c805cf28248d2.css", result);
		});
	}
