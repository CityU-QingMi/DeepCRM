	@Test
	public void testDelegatingFilterProxyNotInjectedWacServletAttrPreferred() throws ServletException, IOException {
		ServletContext sc = new MockServletContext();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);
		wac.refresh();
		sc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		sc.setAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher", wac);

		StaticWebApplicationContext wacToUse = new StaticWebApplicationContext();
		wacToUse.setServletContext(sc);
		String beanName = "targetFilter";
		String attrName = "customAttrName";
		wacToUse.registerSingleton(beanName, MockFilter.class);
		wacToUse.refresh();
		sc.setAttribute(attrName, wacToUse);

		MockFilter targetFilter = (MockFilter) wacToUse.getBean(beanName);

		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy(beanName);
		filterProxy.setContextAttribute(attrName);
		filterProxy.setServletContext(sc);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		filterProxy.doFilter(request, response, null);

		assertNull(targetFilter.filterConfig);
		assertEquals(Boolean.TRUE, request.getAttribute("called"));

		filterProxy.destroy();
		assertNull(targetFilter.filterConfig);
	}
