	@Test
	public void servletConfigAwareWithServletContextAndServletConfig() {
		ServletContext servletContext = new MockServletContext();
		ServletConfig servletConfig = new MockServletConfig(servletContext);
		ServletContextAwareProcessor processor = new ServletContextAwareProcessor(servletContext, servletConfig);
		ServletConfigAwareBean bean = new ServletConfigAwareBean();
		assertNull(bean.getServletConfig());
		processor.postProcessBeforeInitialization(bean, "testBean");
		assertNotNull("ServletConfig should have been set", bean.getServletConfig());
		assertEquals(servletConfig, bean.getServletConfig());
	}
