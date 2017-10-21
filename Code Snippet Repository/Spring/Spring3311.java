	private void doTestLazyResourceInjection(Class<? extends TestBeanHolder> annotatedBeanClass) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		RootBeanDefinition abd = new RootBeanDefinition(annotatedBeanClass);
		abd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		ac.registerBeanDefinition("annotatedBean", abd);
		RootBeanDefinition tbd = new RootBeanDefinition(TestBean.class);
		tbd.setLazyInit(true);
		ac.registerBeanDefinition("testBean", tbd);
		ac.refresh();

		TestBeanHolder bean = ac.getBean("annotatedBean", TestBeanHolder.class);
		assertFalse(ac.getBeanFactory().containsSingleton("testBean"));
		assertNotNull(bean.getTestBean());
		assertNull(bean.getTestBean().getName());
		assertTrue(ac.getBeanFactory().containsSingleton("testBean"));
		TestBean tb = (TestBean) ac.getBean("testBean");
		tb.setName("tb");
		assertSame("tb", bean.getTestBean().getName());
	}
