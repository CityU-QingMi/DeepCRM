	@Test
	public void encodeUrlPreventStringOutOfBounds() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/context-path/index");
		request.setContextPath("/context-path");
		request.setServletPath("");
		request.setAttribute(ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR, this.resourceUrlProvider);
		MockHttpServletResponse response = new MockHttpServletResponse();

		this.filter.doFilter(request, response, (req, res) -> {
			String result = ((HttpServletResponse) res).encodeURL("index?key=value");
			assertEquals("index?key=value", result);
		});
	}
