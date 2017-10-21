	@Test
	public void testGetBeanByTypeInstanceFiltersOutNonAutowireCandidates() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = createConstructorDependencyBeanDefinition(99);
		RootBeanDefinition bd2 = createConstructorDependencyBeanDefinition(43);
		RootBeanDefinition na1 = createConstructorDependencyBeanDefinition(21);
		na1.setAutowireCandidate(false);

		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("na1", na1);
		ConstructorDependency actual = lbf.getBean(ConstructorDependency.class, 42); // na1 was filtered
		assertThat(actual.beanName, equalTo("bd1"));

		lbf.registerBeanDefinition("bd2", bd2);
		try {
			lbf.getBean(TestBean.class, 67);
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
	}
