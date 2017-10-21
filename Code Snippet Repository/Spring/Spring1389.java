	@Test
	public void testOptionalListFieldInjectionWithBeanAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalListFieldInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		OptionalListFieldInjectionBean bean = (OptionalListFieldInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.getTestBean().isPresent());
		assertSame(bf.getBean("testBean"), bean.getTestBean().get().get(0));
		bf.destroySingletons();
	}
