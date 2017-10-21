	@Test
	public void testGenericsBasedMethodInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryMethodInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		String sv = "X";
		bf.registerSingleton("stringValue", sv);
		Integer iv = 1;
		bf.registerSingleton("integerValue", iv);
		StringRepository sr = new StringRepository();
		bf.registerSingleton("stringRepo", sr);
		IntegerRepository ir = new IntegerRepository();
		bf.registerSingleton("integerRepo", ir);

		RepositoryMethodInjectionBean bean = (RepositoryMethodInjectionBean) bf.getBean("annotatedBean");
		assertSame(sv, bean.string);
		assertSame(iv, bean.integer);
		assertSame(1, bean.stringArray.length);
		assertSame(1, bean.integerArray.length);
		assertSame(sv, bean.stringArray[0]);
		assertSame(iv, bean.integerArray[0]);
		assertSame(1, bean.stringList.size());
		assertSame(1, bean.integerList.size());
		assertSame(sv, bean.stringList.get(0));
		assertSame(iv, bean.integerList.get(0));
		assertSame(1, bean.stringMap.size());
		assertSame(1, bean.integerMap.size());
		assertSame(sv, bean.stringMap.get("stringValue"));
		assertSame(iv, bean.integerMap.get("integerValue"));
		assertSame(sr, bean.stringRepository);
		assertSame(ir, bean.integerRepository);
		assertSame(1, bean.stringRepositoryArray.length);
		assertSame(1, bean.integerRepositoryArray.length);
		assertSame(sr, bean.stringRepositoryArray[0]);
		assertSame(ir, bean.integerRepositoryArray[0]);
		assertSame(1, bean.stringRepositoryList.size());
		assertSame(1, bean.integerRepositoryList.size());
		assertSame(sr, bean.stringRepositoryList.get(0));
		assertSame(ir, bean.integerRepositoryList.get(0));
		assertSame(1, bean.stringRepositoryMap.size());
		assertSame(1, bean.integerRepositoryMap.size());
		assertSame(sr, bean.stringRepositoryMap.get("stringRepo"));
		assertSame(ir, bean.integerRepositoryMap.get("integerRepo"));
	}
