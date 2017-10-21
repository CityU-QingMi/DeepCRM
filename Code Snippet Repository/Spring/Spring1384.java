	@Test
	public void testOptionalFieldInjectionWithBeanAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalFieldInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		OptionalFieldInjectionBean bean = (OptionalFieldInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.getTestBean().isPresent());
		assertSame(bf.getBean("testBean"), bean.getTestBean().get());
		bf.destroySingletons();
	}
