	@Test
	public void parameterizedStaticFactoryMethod() {
		RootBeanDefinition rbd = new RootBeanDefinition(Mockito.class);
		rbd.setFactoryMethodName("mock");
		rbd.getConstructorArgumentValues().addGenericArgumentValue(Runnable.class);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("mock", rbd);

		Map<String, Runnable> beans = bf.getBeansOfType(Runnable.class);
		assertEquals(1, beans.size());
	}
