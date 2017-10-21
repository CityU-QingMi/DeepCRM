	@Test
	public void register() throws ServletException {
		initializer.onStartup(servletContext);

		assertEquals(1, servlets.size());
		assertNotNull(servlets.get(SERVLET_NAME));

		DispatcherServlet servlet = (DispatcherServlet) servlets.get(SERVLET_NAME);
		assertEquals(MyDispatcherServlet.class, servlet.getClass());
		WebApplicationContext servletContext = servlet.getWebApplicationContext();

		assertTrue(servletContext.containsBean("bean"));
		assertTrue(servletContext.getBean("bean") instanceof MyBean);

		assertEquals(1, registrations.size());
		assertNotNull(registrations.get(SERVLET_NAME));

		MockServletRegistration registration = registrations.get(SERVLET_NAME);
		assertEquals(Collections.singleton(SERVLET_MAPPING), registration.getMappings());
		assertEquals(1, registration.getLoadOnStartup());
		assertEquals(ROLE_NAME, registration.getRunAsRole());
	}
