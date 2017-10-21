	@Test
	public void testPostConstructAndPreDestroyWithManualConfiguration() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		InitDestroyAnnotationBeanPostProcessor bpp = new InitDestroyAnnotationBeanPostProcessor();
		bpp.setInitAnnotationType(PostConstruct.class);
		bpp.setDestroyAnnotationType(PreDestroy.class);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(AnnotatedInitDestroyBean.class));

		AnnotatedInitDestroyBean bean = (AnnotatedInitDestroyBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		bf.destroySingletons();
		assertTrue(bean.destroyCalled);
	}
