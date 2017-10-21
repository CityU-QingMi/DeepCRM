	@Test
	public void testDelegatingFilterProxyInjectedPreferred() throws ServletException, IOException {
		ServletContext sc = new MockServletContext();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.refresh();
		sc.setAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher", wac);

		StaticWebApplicationContext injectedWac = new StaticWebApplicationContext();
		injectedWac.setServletContext(sc);
		String beanName = "targetFilter";
		injectedWac.registerSingleton(beanName, MockFilter.class);
		injectedWac.refresh();

		MockFilter targetFilter = (MockFilter) injectedWac.getBean(beanName);

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy(beanName, injectedWac);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		assertNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
