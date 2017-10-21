	@Test(timeout = 1000)
	public void testRegistrationOfManySingletonsIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("b", new RootBeanDefinition(B.class));
		// bf.getBean("b");

		for (int i = 0; i < 100000; i++) {
			bf.registerSingleton("a" + i, new A());
		}
	}
