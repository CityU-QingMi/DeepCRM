	private void checkXmlAspect(String appContextFile) {
		ApplicationContext context = new ClassPathXmlApplicationContext(appContextFile, getClass());
		ICounter counter = (ICounter) context.getBean("counter");
		assertTrue("Proxy didn't get created", counter instanceof Advised);

		counter.increment();
		JoinPointMonitorAspect callCountingAspect = (JoinPointMonitorAspect)context.getBean("monitoringAspect");
		assertEquals("Advise didn't get executed", 1, callCountingAspect.beforeExecutions);
		assertEquals("Advise didn't get executed", 1, callCountingAspect.aroundExecutions);
	}
