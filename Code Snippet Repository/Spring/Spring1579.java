	@Ignore
	@Test
	public void testAutowireCandidateWithFieldDescriptor() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		ConstructorArgumentValues cavs1 = new ConstructorArgumentValues();
		cavs1.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person1 = new RootBeanDefinition(Person.class, cavs1, null);
		person1.addQualifier(new AutowireCandidateQualifier(TestQualifier.class));
		lbf.registerBeanDefinition(JUERGEN, person1);
		ConstructorArgumentValues cavs2 = new ConstructorArgumentValues();
		cavs2.addGenericArgumentValue(MARK);
		RootBeanDefinition person2 = new RootBeanDefinition(Person.class, cavs2, null);
		lbf.registerBeanDefinition(MARK, person2);
		DependencyDescriptor qualifiedDescriptor = new DependencyDescriptor(
				QualifiedTestBean.class.getDeclaredField("qualified"), false);
		DependencyDescriptor nonqualifiedDescriptor = new DependencyDescriptor(
				QualifiedTestBean.class.getDeclaredField("nonqualified"), false);
		assertTrue(lbf.isAutowireCandidate(JUERGEN, null));
		assertTrue(lbf.isAutowireCandidate(JUERGEN, nonqualifiedDescriptor));
		assertTrue(lbf.isAutowireCandidate(JUERGEN, qualifiedDescriptor));
		assertTrue(lbf.isAutowireCandidate(MARK, null));
		assertTrue(lbf.isAutowireCandidate(MARK, nonqualifiedDescriptor));
		assertFalse(lbf.isAutowireCandidate(MARK, qualifiedDescriptor));
	}
