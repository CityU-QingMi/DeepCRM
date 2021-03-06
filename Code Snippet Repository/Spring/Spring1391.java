	@Test
	public void testOptionalListMethodInjectionWithBeanAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalListMethodInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		OptionalListMethodInjectionBean bean = (OptionalListMethodInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.getTestBean().isPresent());
		assertSame(bf.getBean("testBean"), bean.getTestBean().get().get(0));
		bf.destroySingletons();
	}
