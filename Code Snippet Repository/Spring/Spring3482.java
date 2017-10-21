	@Test
	public void testStartOrder() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("lifecycleTests.xml", getClass());
		context.start();
		LifecycleTestBean bean1 = (LifecycleTestBean) context.getBean("bean1");
		LifecycleTestBean bean2 = (LifecycleTestBean) context.getBean("bean2");
		LifecycleTestBean bean3 = (LifecycleTestBean) context.getBean("bean3");
		LifecycleTestBean bean4 = (LifecycleTestBean) context.getBean("bean4");
		String notStartedError = "bean was not started";
		assertTrue(notStartedError, bean1.getStartOrder() > 0);
		assertTrue(notStartedError, bean2.getStartOrder() > 0);
		assertTrue(notStartedError, bean3.getStartOrder() > 0);
		assertTrue(notStartedError, bean4.getStartOrder() > 0);
		String orderError = "dependent bean must start after the bean it depends on";
		assertTrue(orderError, bean2.getStartOrder() > bean1.getStartOrder());
		assertTrue(orderError, bean3.getStartOrder() > bean2.getStartOrder());
		assertTrue(orderError, bean4.getStartOrder() > bean2.getStartOrder());
	}
