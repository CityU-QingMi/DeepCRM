	@Test
	public void testCompositeFilter() throws ServletException, IOException {
		ServletContext sc = new MockServletContext();
		MockFilter targetFilter = new MockFilter();
		MockFilterConfig proxyConfig = new MockFilterConfig(sc);

		CompositeFilter filterProxy = new CompositeFilter();
		filterProxy.setFilters(Arrays.asList(targetFilter));
		filterProxy.init(proxyConfig);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		assertNotNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
