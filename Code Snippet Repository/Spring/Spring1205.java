	@Test
	public void testDoubleArrayConstructorWithOptionalAutowiring() throws MalformedURLException {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerSingleton("resource1", new UrlResource("http://localhost:8080"));
		bf.registerSingleton("resource2", new UrlResource("http://localhost:9090"));

		RootBeanDefinition rbd = new RootBeanDefinition(ArrayBean.class);
		rbd.setAutowireMode(RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		bf.registerBeanDefinition("arrayBean", rbd);
		ArrayBean ab = (ArrayBean) bf.getBean("arrayBean");

		assertNull(ab.getIntegerArray());
		assertNull(ab.getResourceArray());
	}
