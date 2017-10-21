	@Test
	public void typedCommandProvidingFormController() throws Exception {
		initServlet(wac -> {
			wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class));
			RootBeanDefinition adapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
			adapterDef.getPropertyValues().add("webBindingInitializer", new MyWebBindingInitializer());
			List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
			argumentResolvers.add(new ServletWebArgumentResolverAdapter(new MySpecialArgumentResolver()));
			adapterDef.getPropertyValues().add("customArgumentResolvers", argumentResolvers);
			wac.registerBeanDefinition("handlerAdapter", adapterDef);
		}, MyTypedCommandProvidingFormController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("defaultName", "10");
		request.addParameter("age", "value2");
		request.addParameter("date", "2007-10-02");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-Integer:10-typeMismatch-tb1-myOriginalValue", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/myOtherPath.do");
		request.addParameter("defaultName", "10");
		request.addParameter("age", "value2");
		request.addParameter("date", "2007-10-02");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-myName-typeMismatch-tb1-myOriginalValue", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/myThirdPath.do");
		request.addParameter("defaultName", "10");
		request.addParameter("age", "100");
		request.addParameter("date", "2007-10-02");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-special-99-special-99", response.getContentAsString());
	}
