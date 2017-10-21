	@Test
	public void overlappingMessageConvertersRequestBody() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			messageConverters.add(new StringHttpMessageConverter());
			messageConverters
					.add(new SimpleMessageConverter(new MediaType("application","json"), MediaType.ALL));
			adapterDef.getPropertyValues().add("messageConverters", messageConverters);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, RequestResponseBodyController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("PUT", "/something");
		request.setContent("Hello World".getBytes("UTF-8"));
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.addHeader("Accept", "application/json, text/javascript, */*");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Invalid content-type", "application/json;charset=ISO-8859-1", response.getHeader("Content-Type"));
	}
