	@Test
	public void noFilters() throws ServletException {
		initializer = new MyAnnotationConfigDispatcherServletInitializer() {
			@Override
			protected Filter[] getServletFilters() {
				return null;
			}
		};

		initializer.onStartup(servletContext);

		assertEquals(0, filterRegistrations.size());
	}
