	@Test(expected = IllegalStateException.class)
	public void testDelegatingFilterProxyWithTargetBeanNameAndNoApplicationContext() throws ServletException, IOException {
		MockServletContext sc = new MockServletContext();

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy("targetFilter", null);
		filterProxy.init(new MockFilterConfig(sc));

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null); // throws
	}
