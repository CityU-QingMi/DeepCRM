	@Test
	public void testAutowireBeanByTypePrimaryTakesPrecedenceOverPriority() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
		RootBeanDefinition bd = new RootBeanDefinition(HighPriorityTestBean.class);
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setPrimary(true);
		lbf.registerBeanDefinition("test", bd);
		lbf.registerBeanDefinition("spouse", bd2);

		DependenciesBean bean = (DependenciesBean)
				lbf.autowire(DependenciesBean.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
		assertThat(bean.getSpouse(), equalTo(lbf.getBean("spouse")));
	}
