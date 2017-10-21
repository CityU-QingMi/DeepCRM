	@Test
	public void asyncSupportedFalse() throws ServletException {
		initializer = new MyAnnotationConfigDispatcherServletInitializer() {
			@Override
			protected boolean isAsyncSupported() {
				return false;
			}
		};

		initializer.onStartup(servletContext);

		MockServletRegistration servletRegistration = servletRegistrations.get(SERVLET_NAME);
		assertFalse(servletRegistration.isAsyncSupported());

		for (MockFilterRegistration filterRegistration : filterRegistrations.values()) {
			assertFalse(filterRegistration.isAsyncSupported());
			assertEquals(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),
					filterRegistration.getMappings().get(SERVLET_NAME));
		}
	}
