	@Test
	public void updateTargetUrl() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.registerSingleton("requestDataValueProcessor", RequestDataValueProcessorWrapper.class);
		wac.setServletContext(new MockServletContext());
		wac.refresh();

		RequestDataValueProcessor mockProcessor = mock(RequestDataValueProcessor.class);
		wac.getBean(RequestDataValueProcessorWrapper.class).setRequestDataValueProcessor(mockProcessor);

		RedirectView rv = new RedirectView();
		rv.setApplicationContext(wac);	// Init RedirectView with WebAppCxt
		rv.setUrl("/path");

		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		given(mockProcessor.processUrl(request, "/path")).willReturn("/path?key=123");

		rv.render(new ModelMap(), request, response);

		verify(mockProcessor).processUrl(request, "/path");
	}
