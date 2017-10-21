	@Test
	public void testSingleConstructorInjectionWithEmptyCollection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(SingleConstructorCollectionInjectionBean.class));
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);

		SingleConstructorCollectionInjectionBean bean = (SingleConstructorCollectionInjectionBean) bf.getBean("annotatedBean");
		assertSame(tb, bean.getTestBean());
		assertNull(bean.getNestedTestBeans());
		bf.destroySingletons();
	}
