	@Test
	public void proxiedFormController() throws Exception {
		initServlet(wac -> {
			wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class));
			DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
			autoProxyCreator.setBeanFactory(wac.getBeanFactory());
			wac.getBeanFactory().addBeanPostProcessor(autoProxyCreator);
			wac.getBeanFactory()
					.registerSingleton("advisor", new DefaultPointcutAdvisor(new SimpleTraceInterceptor()));
		}, MyFormController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("name", "name1");
		request.addParameter("age", "value2");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-name1-typeMismatch-tb1-myValue", response.getContentAsString());
	}
