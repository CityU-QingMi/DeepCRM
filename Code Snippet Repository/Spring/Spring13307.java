	@Test
	public void register() throws ServletException {
		initializer.onStartup(servletContext);

		assertEquals(1, servlets.size());
		assertNotNull(servlets.get(SERVLET_NAME));

		DispatcherServlet servlet = (DispatcherServlet) servlets.get(SERVLET_NAME);
		WebApplicationContext wac = servlet.getWebApplicationContext();
		((AnnotationConfigWebApplicationContext) wac).refresh();

		assertTrue(wac.containsBean("bean"));
		assertTrue(wac.getBean("bean") instanceof MyBean);

		assertEquals(1, servletRegistrations.size());
		assertNotNull(servletRegistrations.get(SERVLET_NAME));

		MockServletRegistration servletRegistration = servletRegistrations.get(SERVLET_NAME);

		assertEquals(Collections.singleton(SERVLET_MAPPING), servletRegistration.getMappings());
		assertEquals(1, servletRegistration.getLoadOnStartup());
		assertEquals(ROLE_NAME, servletRegistration.getRunAsRole());
		assertTrue(servletRegistration.isAsyncSupported());

		assertEquals(4, filterRegistrations.size());
		assertNotNull(filterRegistrations.get("hiddenHttpMethodFilter"));
		assertNotNull(filterRegistrations.get("delegatingFilterProxy"));
		assertNotNull(filterRegistrations.get("delegatingFilterProxy#0"));
		assertNotNull(filterRegistrations.get("delegatingFilterProxy#1"));

		for (MockFilterRegistration filterRegistration : filterRegistrations.values()) {
			assertTrue(filterRegistration.isAsyncSupported());
			assertEquals(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC),
					filterRegistration.getMappings().get(SERVLET_NAME));
		}

	}
