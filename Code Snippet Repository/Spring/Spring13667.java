	@Test
	public void updateTargetUrlWithContextLoader() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.registerSingleton("requestDataValueProcessor", RequestDataValueProcessorWrapper.class);

		MockServletContext servletContext = new MockServletContext();
		ContextLoader contextLoader = new ContextLoader(wac);
		contextLoader.initWebApplicationContext(servletContext);

		try {
			RequestDataValueProcessor mockProcessor = mock(RequestDataValueProcessor.class);
			wac.getBean(RequestDataValueProcessorWrapper.class).setRequestDataValueProcessor(mockProcessor);

			RedirectView rv = new RedirectView();
			rv.setUrl("/path");

			given(mockProcessor.processUrl(request, "/path")).willReturn("/path?key=123");

			rv.render(new ModelMap(), request, response);

			verify(mockProcessor).processUrl(request, "/path");
		}
		finally {
			contextLoader.closeWebApplicationContext(servletContext);
		}
	}
