	@Test
	public void testArrayConstructorWithAutowiring() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerSingleton("integer1", new Integer(4));
		bf.registerSingleton("integer2", new Integer(5));

		RootBeanDefinition rbd = new RootBeanDefinition(ArrayBean.class);
		rbd.setAutowireMode(RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		bf.registerBeanDefinition("arrayBean", rbd);
		ArrayBean ab = (ArrayBean) bf.getBean("arrayBean");

		assertEquals(new Integer(4), ab.getIntegerArray()[0]);
		assertEquals(new Integer(5), ab.getIntegerArray()[1]);
	}
