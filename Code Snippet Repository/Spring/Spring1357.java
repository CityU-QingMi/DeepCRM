	@Test
	public void testFactoryBeanSelfInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SelfInjectingFactoryBean.class));

		SelfInjectingFactoryBean bean = bf.getBean(SelfInjectingFactoryBean.class);
		assertSame(bf.getBean("annotatedBean"), bean.testBean);
	}
