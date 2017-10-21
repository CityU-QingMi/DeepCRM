	@Test
	public void encodeEmptyURLWithContext() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/context/foo");
		request.setContextPath("/context");
		request.setAttribute(ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR, this.resourceUrlProvider);
		MockHttpServletResponse response = new MockHttpServletResponse();

		this.filter.doFilter(request, response, (req, res) -> {
			String result = ((HttpServletResponse) res).encodeURL("?foo=1");
			assertEquals("?foo=1", result);
		});
	}
