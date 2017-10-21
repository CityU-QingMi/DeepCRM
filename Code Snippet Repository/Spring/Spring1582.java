	@Ignore
	@Test
	public void testAutowireCandidateWithConstructorDescriptor() throws Exception {
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
		MethodParameter param = new MethodParameter(QualifiedTestBean.class.getDeclaredConstructor(Person.class), 0);
		DependencyDescriptor qualifiedDescriptor = new DependencyDescriptor(param, false);
		param.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		assertEquals("tpb", param.getParameterName());
		assertTrue(lbf.isAutowireCandidate(JUERGEN, null));
		assertTrue(lbf.isAutowireCandidate(JUERGEN, qualifiedDescriptor));
		assertFalse(lbf.isAutowireCandidate(MARK, qualifiedDescriptor));
	}
