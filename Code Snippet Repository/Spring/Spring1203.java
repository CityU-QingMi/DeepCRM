	@Test
	public void testArrayConstructorWithOptionalAutowiring() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();

		RootBeanDefinition rbd = new RootBeanDefinition(ArrayBean.class);
		rbd.setAutowireMode(RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		bf.registerBeanDefinition("arrayBean", rbd);
		ArrayBean ab = (ArrayBean) bf.getBean("arrayBean");

		assertNull(ab.getIntegerArray());
	}
