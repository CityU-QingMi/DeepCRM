	@Test
	public void pathVariableWithCustomConverter() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition csDef = new RootBeanDefinition(FormattingConversionServiceFactoryBean.class);
			csDef.getPropertyValues().add("converters", new AnnotatedExceptionRaisingConverter());
			RootBeanDefinition wbiDef = new RootBeanDefinition(ConfigurableWebBindingInitializer.class);
			wbiDef.getPropertyValues().add("conversionService", csDef);
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("webBindingInitializer", wbiDef);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, PathVariableWithCustomConverterController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath/1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(404, response.getStatus());
	}
