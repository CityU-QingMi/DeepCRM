	@Test
	public void testBeansStop() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("lifecycleTests.xml", getClass());
		context.start();
		LifecycleTestBean bean1 = (LifecycleTestBean) context.getBean("bean1");
		LifecycleTestBean bean2 = (LifecycleTestBean) context.getBean("bean2");
		LifecycleTestBean bean3 = (LifecycleTestBean) context.getBean("bean3");
		LifecycleTestBean bean4 = (LifecycleTestBean) context.getBean("bean4");
		String startError = "bean was not started";
		assertTrue(startError, bean1.isRunning());
		assertTrue(startError, bean2.isRunning());
		assertTrue(startError, bean3.isRunning());
		assertTrue(startError, bean4.isRunning());
		context.stop();
		String stopError = "bean was not stopped";
		assertFalse(stopError, bean1.isRunning());
		assertFalse(stopError, bean2.isRunning());
		assertFalse(stopError, bean3.isRunning());
		assertFalse(stopError, bean4.isRunning());
	}
