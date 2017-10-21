	@Test
	public void testGetBeanByTypeWithPriorityAndNullInstance() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
		RootBeanDefinition bd1 = new RootBeanDefinition(HighPriorityTestBean.class);
		RootBeanDefinition bd2 = new RootBeanDefinition(NullTestBeanFactoryBean.class);
		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		TestBean bean = lbf.getBean(TestBean.class);
		assertThat(bean.getBeanName(), equalTo("bd1"));
	}
