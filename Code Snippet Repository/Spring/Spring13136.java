	@Test
	public void typeNestedSetBinding() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition csDef = new RootBeanDefinition(FormattingConversionServiceFactoryBean.class);
			csDef.getPropertyValues().add("converters", new TestBeanConverter());
			RootBeanDefinition wbiDef = new RootBeanDefinition(ConfigurableWebBindingInitializer.class);
			wbiDef.getPropertyValues().add("conversionService", csDef);
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("webBindingInitializer", wbiDef);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, NestedSetController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("testBeanSet", "1", "2");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("[1, 2]-org.springframework.tests.sample.beans.TestBean", response.getContentAsString());
	}
