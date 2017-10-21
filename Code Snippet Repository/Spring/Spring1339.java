	@Test
	public void testGenericsBasedFieldInjectionWithSimpleMatchAndMockito() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryFieldInjectionBeanWithSimpleMatch.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);

		RootBeanDefinition rbd = new RootBeanDefinition();
		rbd.setBeanClassName(Mockito.class.getName());
		rbd.setFactoryMethodName("mock");
		// TypedStringValue used to be equivalent to an XML-defined argument String
		rbd.getConstructorArgumentValues().addGenericArgumentValue(new TypedStringValue(Repository.class.getName()));
		bf.registerBeanDefinition("repo", rbd);

		RepositoryFieldInjectionBeanWithSimpleMatch bean = (RepositoryFieldInjectionBeanWithSimpleMatch) bf.getBean("annotatedBean");
		Repository<?> repo = bf.getBean("repo", Repository.class);
		assertSame(repo, bean.repository);
		assertSame(repo, bean.stringRepository);
		assertSame(1, bean.repositoryArray.length);
		assertSame(1, bean.stringRepositoryArray.length);
		assertSame(repo, bean.repositoryArray[0]);
		assertSame(repo, bean.stringRepositoryArray[0]);
		assertSame(1, bean.repositoryList.size());
		assertSame(1, bean.stringRepositoryList.size());
		assertSame(repo, bean.repositoryList.get(0));
		assertSame(repo, bean.stringRepositoryList.get(0));
		assertSame(1, bean.repositoryMap.size());
		assertSame(1, bean.stringRepositoryMap.size());
		assertSame(repo, bean.repositoryMap.get("repo"));
		assertSame(repo, bean.stringRepositoryMap.get("repo"));
	}
