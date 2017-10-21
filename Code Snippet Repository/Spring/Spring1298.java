	@Test
	public void testConstructorInjectionWithCustomMapAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(CustomMapConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		RootBeanDefinition tbm = new RootBeanDefinition(CustomCollectionFactoryMethods.class);
		tbm.setUniqueFactoryMethodName("testBeanMap");
		bf.registerBeanDefinition("myTestBeanMap", tbm);
		bf.registerSingleton("testBean1", new TestBean());
		bf.registerSingleton("testBean2", new TestBean());

		CustomMapConstructorInjectionBean bean = (CustomMapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanMap"), bean.getTestBeanMap());
		bean = (CustomMapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanMap"), bean.getTestBeanMap());
	}
