	@Test
	public void testPostConstructAndPreDestroyWithPostProcessor() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.addBeanPostProcessor(new InitDestroyBeanPostProcessor());
		bf.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(AnnotatedInitDestroyBean.class));

		AnnotatedInitDestroyBean bean = (AnnotatedInitDestroyBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		bf.destroySingletons();
		assertTrue(bean.destroyCalled);
	}
