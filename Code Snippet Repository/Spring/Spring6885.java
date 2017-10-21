	@Test
	public void metaAnnotationIsDiscovered() throws Exception {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class, MetaAnnotationTestBean.class);

		try {
			JmsListenerContainerTestFactory factory = context.getBean(JmsListenerContainerTestFactory.class);
			assertEquals("one container should have been registered", 1, factory.getListenerContainers().size());

			JmsListenerEndpoint endpoint = factory.getListenerContainers().get(0).getEndpoint();
			assertEquals("Wrong endpoint type", MethodJmsListenerEndpoint.class, endpoint.getClass());
			MethodJmsListenerEndpoint methodEndpoint = (MethodJmsListenerEndpoint) endpoint;
			assertEquals(MetaAnnotationTestBean.class, methodEndpoint.getBean().getClass());
			assertEquals(MetaAnnotationTestBean.class.getMethod("handleIt", String.class), methodEndpoint.getMethod());
			assertEquals(MetaAnnotationTestBean.class.getMethod("handleIt", String.class), methodEndpoint.getMostSpecificMethod());
			assertEquals("metaTestQueue", ((AbstractJmsListenerEndpoint) endpoint).getDestination());
		}
		finally {
			context.close();
		}
	}
