	@Test
	public void testOptionalListMethodInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalListMethodInjectionBean.class));

		OptionalListMethodInjectionBean bean = (OptionalListMethodInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bean.getTestBean().isPresent());
		bf.destroySingletons();
	}
