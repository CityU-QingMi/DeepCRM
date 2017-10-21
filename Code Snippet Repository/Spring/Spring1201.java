	@Test
	public void testArrayPropertyWithOptionalAutowiring() throws MalformedURLException {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();

		RootBeanDefinition rbd = new RootBeanDefinition(ArrayBean.class);
		rbd.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
		bf.registerBeanDefinition("arrayBean", rbd);
		ArrayBean ab = (ArrayBean) bf.getBean("arrayBean");

		assertNull(ab.getResourceArray());
	}
