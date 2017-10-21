	@Test
	public void testResourceInjectionWithResolvableDependencyType() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition abd = new RootBeanDefinition(ExtendedResourceInjectionBean.class);
		abd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", abd);
		RootBeanDefinition tbd = new RootBeanDefinition(TestBean.class);
		tbd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("testBean4", tbd);

		bf.registerResolvableDependency(BeanFactory.class, bf);
		bf.registerResolvableDependency(INestedTestBean.class, new ObjectFactory<Object>() {
			@Override
			public Object getObject() throws BeansException {
				return new NestedTestBean();
			}
		});

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.setProperty("tb", "testBean4");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(bf);

		ExtendedResourceInjectionBean bean = (ExtendedResourceInjectionBean) bf.getBean("annotatedBean");
		INestedTestBean tb = bean.getTestBean6();
		assertNotNull(tb);

		ExtendedResourceInjectionBean anotherBean = (ExtendedResourceInjectionBean) bf.getBean("annotatedBean");
		assertNotSame(anotherBean, bean);
		assertNotSame(anotherBean.getTestBean6(), tb);

		String[] depBeans = bf.getDependenciesForBean("annotatedBean");
		assertEquals(1, depBeans.length);
		assertEquals("testBean4", depBeans[0]);
	}
