	@Test
	public void testDelegatingFilterProxyWithFilterDelegateInstance() throws ServletException, IOException {
		MockFilter targetFilter = new MockFilter();

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy(targetFilter);
		filterProxy.init(new MockFilterConfig(new MockServletContext()));

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		assertNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
