	@Test
	public void lazyComponent() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				EnableJmsDefaultContainerFactoryConfig.class, LazyBean.class);
		JmsListenerContainerTestFactory defaultFactory =
				context.getBean("jmsListenerContainerFactory", JmsListenerContainerTestFactory.class);
		assertEquals(0, defaultFactory.getListenerContainers().size());

		context.getBean(LazyBean.class);  // trigger lazy resolution
		assertEquals(1, defaultFactory.getListenerContainers().size());
		MessageListenerTestContainer container = defaultFactory.getListenerContainers().get(0);
		assertTrue("Should have been started " + container, container.isStarted());
		context.close();  // close and stop the listeners
		assertTrue("Should have been stopped " + container, container.isStopped());
	}
