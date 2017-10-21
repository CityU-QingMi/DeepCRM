	@Test
	public void testConstructorInjectionWithPlainMapAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(MapConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		RootBeanDefinition tbm = new RootBeanDefinition(CollectionFactoryMethods.class);
		tbm.setUniqueFactoryMethodName("testBeanMap");
		bf.registerBeanDefinition("myTestBeanMap", tbm);
		bf.registerSingleton("otherMap", new HashMap<>());

		MapConstructorInjectionBean bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanMap"), bean.getTestBeanMap());
		bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanMap"), bean.getTestBeanMap());
	}
