	@Test
	public void testOptionalListFieldInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalListFieldInjectionBean.class));

		OptionalListFieldInjectionBean bean = (OptionalListFieldInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bean.getTestBean().isPresent());
		bf.destroySingletons();
	}
