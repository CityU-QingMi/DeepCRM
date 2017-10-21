	@Test
	public void responseBodyArgMismatch() throws Exception {
		initServlet(wac -> {
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setClassesToBeBound(A.class, B.class);
			try {
				marshaller.afterPropertiesSet();
			}
			catch (Exception ex) {
				throw new BeanCreationException(ex.getMessage(), ex);
			}
			MarshallingHttpMessageConverter messageConverter = new MarshallingHttpMessageConverter(marshaller);

			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("messageConverters", messageConverter);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, RequestBodyArgMismatchController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("PUT", "/something");
		String requestBody = "<b/>";
		request.setContent(requestBody.getBytes("UTF-8"));
		request.addHeader("Content-Type", "application/xml; charset=utf-8");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(400, response.getStatus());
	}
