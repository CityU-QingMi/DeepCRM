	@Test
	public void mavResolver() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			ModelAndViewResolver[] mavResolvers = new ModelAndViewResolver[] {new MyModelAndViewResolver()};
			adapterDef.getPropertyValues().add("modelAndViewResolvers", mavResolvers);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, ModelAndViewResolverController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myValue", response.getContentAsString());

	}
