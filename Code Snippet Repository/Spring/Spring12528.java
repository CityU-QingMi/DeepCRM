	@Test
	public void servletContextAwareWithServletConfig() {
		ServletContext servletContext = new MockServletContext();
		ServletConfig servletConfig = new MockServletConfig(servletContext);
		ServletContextAwareProcessor processor = new ServletContextAwareProcessor(servletConfig);
		ServletContextAwareBean bean = new ServletContextAwareBean();
		assertNull(bean.getServletContext());
		processor.postProcessBeforeInitialization(bean, "testBean");
		assertNotNull("ServletContext should have been set", bean.getServletContext());
		assertEquals(servletContext, bean.getServletContext());
	}
