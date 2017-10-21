	@Test
	public void parameterCsvAsStringArray() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition csDef = new RootBeanDefinition(FormattingConversionServiceFactoryBean.class);
			RootBeanDefinition wbiDef = new RootBeanDefinition(ConfigurableWebBindingInitializer.class);
			wbiDef.getPropertyValues().add("conversionService", csDef);
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("webBindingInitializer", wbiDef);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, CsvController.class);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/integerArray");
		request.setMethod("POST");
		request.addParameter("content", "1,2");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("1-2", response.getContentAsString());
	}
