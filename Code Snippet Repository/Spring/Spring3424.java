	@Test
	public void listenersInApplicationContext() {
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerBeanDefinition("listener1", new RootBeanDefinition(MyOrderedListener1.class));
		RootBeanDefinition listener2 = new RootBeanDefinition(MyOrderedListener2.class);
		listener2.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("listener1"));
		listener2.setLazyInit(true);
		context.registerBeanDefinition("listener2", listener2);
		context.refresh();
		assertFalse(context.getDefaultListableBeanFactory().containsSingleton("listener2"));

		MyOrderedListener1 listener1 = context.getBean("listener1", MyOrderedListener1.class);
		MyOtherEvent event1 = new MyOtherEvent(context);
		context.publishEvent(event1);
		assertFalse(context.getDefaultListableBeanFactory().containsSingleton("listener2"));
		MyEvent event2 = new MyEvent(context);
		context.publishEvent(event2);
		assertTrue(context.getDefaultListableBeanFactory().containsSingleton("listener2"));
		MyEvent event3 = new MyEvent(context);
		context.publishEvent(event3);
		MyOtherEvent event4 = new MyOtherEvent(context);
		context.publishEvent(event4);
		assertTrue(listener1.seenEvents.contains(event1));
		assertTrue(listener1.seenEvents.contains(event2));
		assertTrue(listener1.seenEvents.contains(event3));
		assertTrue(listener1.seenEvents.contains(event4));

		listener1.seenEvents.clear();
		context.publishEvent(event1);
		context.publishEvent(event2);
		context.publishEvent(event3);
		context.publishEvent(event4);
		assertTrue(listener1.seenEvents.contains(event1));
		assertTrue(listener1.seenEvents.contains(event2));
		assertTrue(listener1.seenEvents.contains(event3));
		assertTrue(listener1.seenEvents.contains(event4));

		AbstractApplicationEventMulticaster multicaster = context.getBean(AbstractApplicationEventMulticaster.class);
		assertEquals(2, multicaster.retrieverCache.size());

		context.close();
	}
