	@Test(timeout = 1000)
	public void testByTypeLookupIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();

		for (int i = 0; i < 1000; i++) {
			bf.registerBeanDefinition("a" + i, new RootBeanDefinition(A.class));
		}
		bf.registerBeanDefinition("b", new RootBeanDefinition(B.class));

		bf.freezeConfiguration();

		for (int i = 0; i < 10000; i++) {
			bf.getBean(B.class);
		}
	}
