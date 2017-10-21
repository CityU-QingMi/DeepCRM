	@Test
	public void testGenericsBasedFactoryBeanInjectionWithBeanDefinition() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryFactoryBeanInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		bf.registerBeanDefinition("repoFactoryBean", new RootBeanDefinition(RepositoryFactoryBean.class));

		RepositoryFactoryBeanInjectionBean bean = (RepositoryFactoryBeanInjectionBean) bf.getBean("annotatedBean");
		RepositoryFactoryBean<?> repoFactoryBean = bf.getBean("&repoFactoryBean", RepositoryFactoryBean.class);
		assertSame(repoFactoryBean, bean.repositoryFactoryBean);
	}
