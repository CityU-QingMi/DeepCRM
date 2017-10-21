	@Test
	public void testOptionalFieldInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalFieldInjectionBean.class));

		OptionalFieldInjectionBean bean = (OptionalFieldInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bean.getTestBean().isPresent());
		bf.destroySingletons();
	}
