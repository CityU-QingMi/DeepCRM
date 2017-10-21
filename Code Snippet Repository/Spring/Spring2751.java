	private void doTestTwoAdviceAspectWith(String location) {
		ClassPathXmlApplicationContext bf = newContext(location);

		boolean aspectSingleton = bf.isSingleton("aspect");
		ITestBean adrian1 = (ITestBean) bf.getBean("adrian");
		testPrototype(adrian1, 0);
		ITestBean adrian2 = (ITestBean) bf.getBean("adrian");
		assertNotSame(adrian1, adrian2);
		testPrototype(adrian2, aspectSingleton ? 2 : 0);
	}
