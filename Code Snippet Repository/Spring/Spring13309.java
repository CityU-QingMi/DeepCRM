	@Test
	public void rootContextOnly() throws ServletException {
		initializer = new MyAnnotationConfigDispatcherServletInitializer() {
			@Override
			protected Class<?>[] getRootConfigClasses() {
				return new Class<?>[] {MyConfiguration.class};
			}
			@Override
			protected Class<?>[] getServletConfigClasses() {
				return null;
			}
		};

		initializer.onStartup(servletContext);

		DispatcherServlet servlet = (DispatcherServlet) servlets.get(SERVLET_NAME);
		servlet.init(new MockServletConfig(this.servletContext));

		WebApplicationContext wac = servlet.getWebApplicationContext();
		((AnnotationConfigWebApplicationContext) wac).refresh();

		assertTrue(wac.containsBean("bean"));
		assertTrue(wac.getBean("bean") instanceof MyBean);
	}
