	@Test
	public void responseBodyAsHtmlWithProducesCondition() throws Exception {
		initServlet(wac -> {
			ContentNegotiationManagerFactoryBean factoryBean = new ContentNegotiationManagerFactoryBean();
			factoryBean.afterPropertiesSet();
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("contentNegotiationManager", factoryBean.getObject());
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, TextRestController.class);

		byte[] content = "alert('boo')".getBytes(StandardCharsets.ISO_8859_1);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/a3.html");
		request.setContent(content);
		MockHttpServletResponse response = new MockHttpServletResponse();

		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("text/html;charset=ISO-8859-1", response.getContentType());
		assertNull(response.getHeader("Content-Disposition"));
		assertArrayEquals(content, response.getContentAsByteArray());
	}
