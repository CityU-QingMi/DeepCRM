	@Test
	public void resourceLoaderPath() throws Exception {
		MockServletContext servletContext = new MockServletContext();
		this.wac.setServletContext(servletContext);
		this.wac.refresh();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.wac);
		MockHttpServletResponse response = new MockHttpServletResponse();
		Map<String, Object> model = new HashMap<>();
		InvocableScriptEngine engine = mock(InvocableScriptEngine.class);
		when(engine.invokeFunction(any(), any(), any(), any())).thenReturn("foo");
		this.view.setEngine(engine);
		this.view.setRenderFunction("render");
		this.view.setApplicationContext(this.wac);
		this.view.setUrl("org/springframework/web/servlet/view/script/empty.txt");
		this.view.render(model, request, response);
		assertEquals("foo", response.getContentAsString());

		response = new MockHttpServletResponse();
		this.view.setResourceLoaderPath("classpath:org/springframework/web/servlet/view/script/");
		this.view.setUrl("empty.txt");
		this.view.render(model, request, response);
		assertEquals("foo", response.getContentAsString());

		response = new MockHttpServletResponse();
		this.view.setResourceLoaderPath("classpath:org/springframework/web/servlet/view/script");
		this.view.setUrl("empty.txt");
		this.view.render(model, request, response);
		assertEquals("foo", response.getContentAsString());
	}
