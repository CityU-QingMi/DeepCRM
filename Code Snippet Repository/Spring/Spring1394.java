	@Test
	public void testProviderOfOptionalFieldInjectionWithBeanNotAvailable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ProviderOfOptionalFieldInjectionBean.class));

		ProviderOfOptionalFieldInjectionBean bean = (ProviderOfOptionalFieldInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bean.getTestBean().isPresent());
		bf.destroySingletons();
	}
