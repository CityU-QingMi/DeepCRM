	@Test
	public void unsupportedRequestBody() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("messageConverters", new ByteArrayHttpMessageConverter());
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, RequestResponseBodyController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("PUT", "/something");
		String requestBody = "Hello World";
		request.setContent(requestBody.getBytes("UTF-8"));
		request.addHeader("Content-Type", "application/pdf");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(415, response.getStatus());
		assertNotNull("No Accept response header set", response.getHeader("Accept"));
	}
