	@Test
	public void responseBodyNoAcceptableMediaType() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			StringHttpMessageConverter converter = new StringHttpMessageConverter();
			adapterDef.getPropertyValues().add("messageConverters", converter);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, RequestResponseBodyProducesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("PUT", "/something");
		String requestBody = "Hello World";
		request.setContent(requestBody.getBytes("UTF-8"));
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.addHeader("Accept", "application/pdf, application/msword");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(406, response.getStatus());
	}
