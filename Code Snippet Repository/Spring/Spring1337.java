	@Test
	public void testGenericsBasedFactoryBeanInjectionWithSingletonBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryFactoryBeanInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		bf.registerSingleton("repoFactoryBean", new RepositoryFactoryBean<>());

		RepositoryFactoryBeanInjectionBean bean = (RepositoryFactoryBeanInjectionBean) bf.getBean("annotatedBean");
		RepositoryFactoryBean<?> repoFactoryBean = bf.getBean("&repoFactoryBean", RepositoryFactoryBean.class);
		assertSame(repoFactoryBean, bean.repositoryFactoryBean);
	}
