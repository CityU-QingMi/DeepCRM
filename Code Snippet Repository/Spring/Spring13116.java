	@Test
	public void requestMappingInterfaceWithProxy() throws Exception {
		initServlet(wac -> {
			DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
			autoProxyCreator.setBeanFactory(wac.getBeanFactory());
			wac.getBeanFactory().addBeanPostProcessor(autoProxyCreator);
			wac.getBeanFactory().registerSingleton("advisor", new DefaultPointcutAdvisor(new SimpleTraceInterceptor()));
		}, IMyControllerImpl.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/handle");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle null", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/handle");
		request.addParameter("p", "value");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle value", response.getContentAsString());
	}
