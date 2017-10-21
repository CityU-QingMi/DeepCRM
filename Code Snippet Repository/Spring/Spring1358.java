	@Test
	public void testFactoryBeanSelfInjectionViaFactoryMethod() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(SelfInjectingFactoryBean.class);
		bd.setFactoryMethodName("create");
		bf.registerBeanDefinition("annotatedBean", bd);

		SelfInjectingFactoryBean bean = bf.getBean(SelfInjectingFactoryBean.class);
		assertSame(bf.getBean("annotatedBean"), bean.testBean);
	}
