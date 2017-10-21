	@Test
	public void servletContextAwareWithServletContext() {
		ServletContext servletContext = new MockServletContext();
		ServletContextAwareProcessor processor = new ServletContextAwareProcessor(servletContext);
		ServletContextAwareBean bean = new ServletContextAwareBean();
		assertNull(bean.getServletContext());
		processor.postProcessBeforeInitialization(bean, "testBean");
		assertNotNull("ServletContext should have been set", bean.getServletContext());
		assertEquals(servletContext, bean.getServletContext());
	}
