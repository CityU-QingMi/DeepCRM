	@Test
	public void testSizeConstraintSatisfied() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("bvpp", new RootBeanDefinition(BeanValidationPostProcessor.class));
		RootBeanDefinition bd = new RootBeanDefinition(NotNullConstrainedBean.class);
		bd.getPropertyValues().add("testBean", new TestBean());
		bd.getPropertyValues().add("stringValue", "ss");
		ac.registerBeanDefinition("bean", bd);
		ac.refresh();
		ac.close();
	}
