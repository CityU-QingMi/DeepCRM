	@Test
	public void sendToAnnotationFoundOnProxy() throws Exception {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class, ProxyConfig.class, ProxyTestBean.class);
		try {
			JmsListenerContainerTestFactory factory = context.getBean(JmsListenerContainerTestFactory.class);
			assertEquals("one container should have been registered", 1, factory.getListenerContainers().size());

			JmsListenerEndpoint endpoint = factory.getListenerContainers().get(0).getEndpoint();
			assertEquals("Wrong endpoint type", MethodJmsListenerEndpoint.class, endpoint.getClass());
			MethodJmsListenerEndpoint methodEndpoint = (MethodJmsListenerEndpoint) endpoint;
			assertTrue(AopUtils.isJdkDynamicProxy(methodEndpoint.getBean()));
			assertTrue(methodEndpoint.getBean() instanceof SimpleService);
			assertEquals(SimpleService.class.getMethod("handleIt", String.class), methodEndpoint.getMethod());
			assertEquals(ProxyTestBean.class.getMethod("handleIt", String.class), methodEndpoint.getMostSpecificMethod());

			Method m = ReflectionUtils.findMethod(endpoint.getClass(), "getDefaultResponseDestination");
			ReflectionUtils.makeAccessible(m);
			Object destination = ReflectionUtils.invokeMethod(m, endpoint);
			assertEquals("SendTo annotation not found on proxy", "foobar", destination);
		}
		finally {
			context.close();
		}
	}
