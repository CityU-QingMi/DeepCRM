	@Test
	public void testDelegatingFilterProxyWithTargetBeanNameAndNotYetRefreshedApplicationContext() throws ServletException, IOException {
		MockServletContext sc = new MockServletContext();

		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.registerSingleton("targetFilter", MockFilter.class);
		// wac.refresh();
		// note that the context is not set as the ROOT attribute in the ServletContext!

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy("targetFilter", wac);
		filterProxy.init(new MockFilterConfig(sc));

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		MockFilter targetFilter = (MockFilter) wac.getBean("targetFilter");

		assertNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
