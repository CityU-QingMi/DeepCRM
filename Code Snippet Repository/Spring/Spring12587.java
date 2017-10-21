	@Test
	public void dispatcherServletRefresh() throws ServletException {
		MockServletContext servletContext = new MockServletContext("org/springframework/web/context");
		DispatcherServlet servlet = new DispatcherServlet();

		servlet.init(new MockServletConfig(servletContext, "empty"));
		ServletContextAwareBean contextBean =
				(ServletContextAwareBean) servlet.getWebApplicationContext().getBean("servletContextAwareBean");
		ServletConfigAwareBean configBean =
				(ServletConfigAwareBean) servlet.getWebApplicationContext().getBean("servletConfigAwareBean");
		assertSame(servletContext, contextBean.getServletContext());
		assertSame(servlet.getServletConfig(), configBean.getServletConfig());
		MultipartResolver multipartResolver = servlet.getMultipartResolver();
		assertNotNull(multipartResolver);

		servlet.refresh();

		ServletContextAwareBean contextBean2 =
				(ServletContextAwareBean) servlet.getWebApplicationContext().getBean("servletContextAwareBean");
		ServletConfigAwareBean configBean2 =
				(ServletConfigAwareBean) servlet.getWebApplicationContext().getBean("servletConfigAwareBean");
		assertSame(servletContext, contextBean2.getServletContext());
		assertSame(servlet.getServletConfig(), configBean2.getServletConfig());
		assertNotSame(contextBean, contextBean2);
		assertNotSame(configBean, configBean2);
		MultipartResolver multipartResolver2 = servlet.getMultipartResolver();
		assertNotSame(multipartResolver, multipartResolver2);

		servlet.destroy();
	}
