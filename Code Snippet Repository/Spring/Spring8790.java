	@Test
	public void dispatcherServletCustomizerProcessedInOrder() {
		StubWebApplicationContext root = new StubWebApplicationContext(this.servletContext);
		DefaultMockMvcBuilder builder = webAppContextSetup(root);
		builder.addDispatcherServletCustomizer(ds -> ds.setContextId("test-id"));
		builder.addDispatcherServletCustomizer(ds -> ds.setContextId("override-id"));
		builder.dispatchOptions(true);
		MockMvc mvc = builder.build();
		DispatcherServlet ds = (DispatcherServlet) new DirectFieldAccessor(mvc)
				.getPropertyValue("servlet");
		assertEquals("override-id", ds.getContextId());
	}
