	@Test
	public void testObjectFactoryQualifierInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ObjectFactoryQualifierInjectionBean.class));
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "testBean"));
		bf.registerBeanDefinition("dependencyBean", bd);
		bf.registerBeanDefinition("dependencyBean2", new RootBeanDefinition(TestBean.class));

		ObjectFactoryQualifierInjectionBean bean = (ObjectFactoryQualifierInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("dependencyBean"), bean.getTestBean());
		bf.destroySingletons();
	}
