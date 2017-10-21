	@Test
	public void testNotNullConstraintAfterInitialization() {
		GenericApplicationContext ac = new GenericApplicationContext();
		RootBeanDefinition bvpp = new RootBeanDefinition(BeanValidationPostProcessor.class);
		bvpp.getPropertyValues().add("afterInitialization", true);
		ac.registerBeanDefinition("bvpp", bvpp);
		ac.registerBeanDefinition("capp", new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class));
		ac.registerBeanDefinition("bean", new RootBeanDefinition(AfterInitConstraintBean.class));
		ac.refresh();
		ac.close();
	}
