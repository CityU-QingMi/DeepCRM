	@Test
	public void testStopOrder() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("lifecycleTests.xml", getClass());
		context.start();
		context.stop();
		LifecycleTestBean bean1 = (LifecycleTestBean) context.getBean("bean1");
		LifecycleTestBean bean2 = (LifecycleTestBean) context.getBean("bean2");
		LifecycleTestBean bean3 = (LifecycleTestBean) context.getBean("bean3");
		LifecycleTestBean bean4 = (LifecycleTestBean) context.getBean("bean4");
		String notStoppedError = "bean was not stopped";
		assertTrue(notStoppedError, bean1.getStopOrder() > 0);
		assertTrue(notStoppedError, bean2.getStopOrder() > 0);
		assertTrue(notStoppedError, bean3.getStopOrder() > 0);
		assertTrue(notStoppedError, bean4.getStopOrder() > 0);
		String orderError = "dependent bean must stop before the bean it depends on";
		assertTrue(orderError, bean2.getStopOrder() < bean1.getStopOrder());
		assertTrue(orderError, bean3.getStopOrder() < bean2.getStopOrder());
		assertTrue(orderError, bean4.getStopOrder() < bean2.getStopOrder());
	}
