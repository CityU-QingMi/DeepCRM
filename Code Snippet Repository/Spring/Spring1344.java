	@Test
	@SuppressWarnings("")
	public void testGenericsBasedConstructorInjectionWithNonTypedTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		GenericRepository gr = new GenericRepository();
		bf.registerSingleton("genericRepo", gr);

		RepositoryConstructorInjectionBean bean = (RepositoryConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(gr, bean.stringRepository);
		assertSame(gr, bean.integerRepository);
		assertSame(1, bean.stringRepositoryArray.length);
		assertSame(1, bean.integerRepositoryArray.length);
		assertSame(gr, bean.stringRepositoryArray[0]);
		assertSame(gr, bean.integerRepositoryArray[0]);
		assertSame(1, bean.stringRepositoryList.size());
		assertSame(1, bean.integerRepositoryList.size());
		assertSame(gr, bean.stringRepositoryList.get(0));
		assertSame(gr, bean.integerRepositoryList.get(0));
		assertSame(1, bean.stringRepositoryMap.size());
		assertSame(1, bean.integerRepositoryMap.size());
		assertSame(gr, bean.stringRepositoryMap.get("genericRepo"));
		assertSame(gr, bean.integerRepositoryMap.get("genericRepo"));
	}
