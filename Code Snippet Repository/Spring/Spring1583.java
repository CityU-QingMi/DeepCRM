	@Ignore
	@Test
	public void testAutowireCandidateWithMethodDescriptor() throws Exception {
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
		MethodParameter qualifiedParam =
				new MethodParameter(QualifiedTestBean.class.getDeclaredMethod("autowireQualified", Person.class), 0);
		MethodParameter nonqualifiedParam =
				new MethodParameter(QualifiedTestBean.class.getDeclaredMethod("autowireNonqualified", Person.class), 0);
		DependencyDescriptor qualifiedDescriptor = new DependencyDescriptor(qualifiedParam, false);
		DependencyDescriptor nonqualifiedDescriptor = new DependencyDescriptor(nonqualifiedParam, false);
		qualifiedParam.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		assertEquals("tpb", qualifiedParam.getParameterName());
		nonqualifiedParam.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		assertEquals("tpb", nonqualifiedParam.getParameterName());
		assertTrue(lbf.isAutowireCandidate(JUERGEN, null));
		assertTrue(lbf.isAutowireCandidate(JUERGEN, nonqualifiedDescriptor));
		assertTrue(lbf.isAutowireCandidate(JUERGEN, qualifiedDescriptor));
		assertTrue(lbf.isAutowireCandidate(MARK, null));
		assertTrue(lbf.isAutowireCandidate(MARK, nonqualifiedDescriptor));
		assertFalse(lbf.isAutowireCandidate(MARK, qualifiedDescriptor));
	}
