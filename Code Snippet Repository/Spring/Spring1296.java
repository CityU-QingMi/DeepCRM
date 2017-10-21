	@Test
	public void testConstructorInjectionWithTypedMapAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(MapConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		MyTestBeanMap tbm = new MyTestBeanMap();
		tbm.put("testBean1", new TestBean("tb1"));
		tbm.put("testBean2", new TestBean("tb2"));
		bf.registerSingleton("testBeans", tbm);
		bf.registerSingleton("otherMap", new Properties());

		MapConstructorInjectionBean bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(tbm, bean.getTestBeanMap());
		bean = (MapConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(tbm, bean.getTestBeanMap());
	}
