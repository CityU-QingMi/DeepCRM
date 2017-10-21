	@Test
	public void testObjectFactoryFieldInjectionIntoPrototypeBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition annotatedBeanDefinition = new RootBeanDefinition(ObjectFactoryQualifierFieldInjectionBean.class);
		annotatedBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", annotatedBeanDefinition);
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "testBean"));
		bf.registerBeanDefinition("testBean", bd);
		bf.registerBeanDefinition("testBean2", new RootBeanDefinition(TestBean.class));

		ObjectFactoryQualifierFieldInjectionBean bean = (ObjectFactoryQualifierFieldInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("testBean"), bean.getTestBean());
		ObjectFactoryQualifierFieldInjectionBean anotherBean = (ObjectFactoryQualifierFieldInjectionBean) bf.getBean("annotatedBean");
		assertNotSame(anotherBean, bean);
		assertSame(bf.getBean("testBean"), bean.getTestBean());
	}
