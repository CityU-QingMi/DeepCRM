	@Test
	public void contentType() throws Exception {
		MockServletContext servletContext = new MockServletContext();
		this.wac.setServletContext(servletContext);
		this.wac.refresh();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.wac);
		MockHttpServletResponse response = new MockHttpServletResponse();
		Map<String, Object> model = new HashMap<>();
		this.view.setEngine(mock(InvocableScriptEngine.class));
		this.view.setRenderFunction("render");
		this.view.setResourceLoaderPath("classpath:org/springframework/web/servlet/view/script/");
		this.view.setUrl("empty.txt");
		this.view.setApplicationContext(this.wac);

		this.view.render(model, request, response);
		assertEquals(MediaType.TEXT_HTML_VALUE + ";charset=" +
				StandardCharsets.UTF_8, response.getHeader(HttpHeaders.CONTENT_TYPE));

		response = new MockHttpServletResponse();
		this.view.setContentType(MediaType.TEXT_PLAIN_VALUE);
		this.view.render(model, request, response);
		assertEquals(MediaType.TEXT_PLAIN_VALUE + ";charset=" +
				StandardCharsets.UTF_8, response.getHeader(HttpHeaders.CONTENT_TYPE));

		response = new MockHttpServletResponse();
		this.view.setCharset(StandardCharsets.ISO_8859_1);
		this.view.render(model, request, response);
		assertEquals(MediaType.TEXT_PLAIN_VALUE + ";charset=" +
				StandardCharsets.ISO_8859_1, response.getHeader(HttpHeaders.CONTENT_TYPE));

	}
