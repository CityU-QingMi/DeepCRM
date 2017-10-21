	@Test
	public void testPostConstructAndPreDestroyWithApplicationContextAndPostProcessor() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBeanDefinition("bpp1", new RootBeanDefinition(InitDestroyBeanPostProcessor.class));
		ctx.registerBeanDefinition("bpp2", new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class));
		ctx.registerBeanDefinition("annotatedBean", new RootBeanDefinition(AnnotatedInitDestroyBean.class));
		ctx.refresh();

		AnnotatedInitDestroyBean bean = (AnnotatedInitDestroyBean) ctx.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		ctx.close();
		assertTrue(bean.destroyCalled);
	}
