	@Test
	public void testDelegatingFilterProxyNotInjectedWithRootPreferred() throws ServletException, IOException {
		ServletContext sc = new MockServletContext();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.refresh();
		sc.setAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher", wac);
		sc.setAttribute("another", wac);

		StaticWebApplicationContext wacToUse = new StaticWebApplicationContext();
		wacToUse.setServletContext(sc);
		String beanName = "targetFilter";
		wacToUse.registerSingleton(beanName, MockFilter.class);
		wacToUse.refresh();
		sc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wacToUse);

		MockFilter targetFilter = (MockFilter) wacToUse.getBean(beanName);

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy(beanName);
		filterProxy.setServletContext(sc);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		assertNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
