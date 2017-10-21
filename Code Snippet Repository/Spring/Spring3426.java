	@Test
	public void listenersInApplicationContextWithNestedChild() {
		StaticApplicationContext context = new StaticApplicationContext();
		RootBeanDefinition nestedChild = new RootBeanDefinition(StaticApplicationContext.class);
		nestedChild.getPropertyValues().add("parent", context);
		nestedChild.setInitMethodName("refresh");
		context.registerBeanDefinition("nestedChild", nestedChild);
		RootBeanDefinition listener1Def = new RootBeanDefinition(MyOrderedListener1.class);
		listener1Def.setDependsOn("nestedChild");
		context.registerBeanDefinition("listener1", listener1Def);
		context.refresh();

		MyOrderedListener1 listener1 = context.getBean("listener1", MyOrderedListener1.class);
		MyEvent event1 = new MyEvent(context);
		context.publishEvent(event1);
		assertTrue(listener1.seenEvents.contains(event1));

		SimpleApplicationEventMulticaster multicaster = context.getBean(
				AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
				SimpleApplicationEventMulticaster.class);
		assertFalse(multicaster.getApplicationListeners().isEmpty());

		context.close();
		assertTrue(multicaster.getApplicationListeners().isEmpty());
	}
