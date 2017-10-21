	@Test
	public void genericsBasedInjectionWithScopedProxy() {
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(RepositoryInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		beanFactory.registerBeanDefinition("annotatedBean", bd);
		beanFactory.registerBeanDefinition("configClass", new RootBeanDefinition(ScopedProxyRepositoryConfiguration.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);
		beanFactory.freezeConfiguration();

		RepositoryInjectionBean bean = (RepositoryInjectionBean) beanFactory.getBean("annotatedBean");
		assertEquals("Repository<String>", bean.stringRepository.toString());
		assertEquals("Repository<Integer>", bean.integerRepository.toString());
		assertTrue(AopUtils.isCglibProxy(bean.stringRepository));
		assertTrue(AopUtils.isCglibProxy(bean.integerRepository));
	}
