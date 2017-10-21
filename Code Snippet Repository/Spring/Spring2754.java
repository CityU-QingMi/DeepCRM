	private long testRepeatedAroundAdviceInvocations(String file, int howmany, String technology) {
		ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext(file, CLASS);

		StopWatch sw = new StopWatch();
		sw.start(howmany + " repeated around advice invocations with " + technology);
		ITestBean adrian = (ITestBean) bf.getBean("adrian");

		assertTrue(AopUtils.isAopProxy(adrian));
		assertEquals(68, adrian.getAge());

		for (int i = 0; i < howmany; i++) {
			adrian.getAge();
		}

		sw.stop();
		System.out.println(sw.prettyPrint());
		return sw.getLastTaskTimeMillis();
	}
