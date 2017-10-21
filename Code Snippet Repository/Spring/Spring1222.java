	@Test
	public void testGetBeanByTypeWithMultiplePriority() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
		RootBeanDefinition bd1 = new RootBeanDefinition(HighPriorityTestBean.class);
		RootBeanDefinition bd2 = new RootBeanDefinition(HighPriorityTestBean.class);
		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		thrown.expect(NoUniqueBeanDefinitionException.class);
		thrown.expectMessage(containsString("Multiple beans found with the same priority"));
		thrown.expectMessage(containsString("5")); // conflicting priority
		lbf.getBean(TestBean.class);
	}
