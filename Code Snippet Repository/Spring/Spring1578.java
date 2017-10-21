	@Test
	public void testAutowireCandidateExplicitlyFalseWithIrrelevantDescriptor() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition rbd = new RootBeanDefinition(Person.class, cavs, null);
		rbd.setAutowireCandidate(false);
		lbf.registerBeanDefinition(JUERGEN, rbd);
		assertFalse(lbf.isAutowireCandidate(JUERGEN, null));
		assertFalse(lbf.isAutowireCandidate(JUERGEN,
				new DependencyDescriptor(Person.class.getDeclaredField("name"), false)));
		assertFalse(lbf.isAutowireCandidate(JUERGEN,
				new DependencyDescriptor(Person.class.getDeclaredField("name"), true)));
	}
