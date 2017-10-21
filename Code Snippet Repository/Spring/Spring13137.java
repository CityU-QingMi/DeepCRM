	@Test
	public void responseBodyAsTextWithCssExtension() throws Exception {
		initServlet(wac -> {
			ContentNegotiationManagerFactoryBean factoryBean = new ContentNegotiationManagerFactoryBean();
			factoryBean.afterPropertiesSet();
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("contentNegotiationManager", factoryBean.getObject());
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, TextRestController.class);

		byte[] content = "body".getBytes(StandardCharsets.ISO_8859_1);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/a4.css");
		request.setContent(content);
		MockHttpServletResponse response = new MockHttpServletResponse();

		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("text/css;charset=ISO-8859-1", response.getContentType());
		assertNull(response.getHeader("Content-Disposition"));
		assertArrayEquals(content, response.getContentAsByteArray());
	}
