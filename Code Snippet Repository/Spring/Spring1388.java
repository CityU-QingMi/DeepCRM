	@Test
	public void testOptionalMethodInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(OptionalMethodInjectionBean.class));

		OptionalMethodInjectionBean bean = (OptionalMethodInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bean.getTestBean().isPresent());
		bf.destroySingletons();
	}
