	@Test
	public void testLazyResourceInjectionWithField() {
		doTestLazyResourceInjection(FieldResourceInjectionBean.class);

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		RootBeanDefinition abd = new RootBeanDefinition(FieldResourceInjectionBean.class);
		abd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		ac.registerBeanDefinition("annotatedBean", abd);
		RootBeanDefinition tbd = new RootBeanDefinition(TestBean.class);
		tbd.setLazyInit(true);
		ac.registerBeanDefinition("testBean", tbd);
		ac.refresh();

		FieldResourceInjectionBean bean = ac.getBean("annotatedBean", FieldResourceInjectionBean.class);
		assertFalse(ac.getBeanFactory().containsSingleton("testBean"));
		assertFalse(bean.getTestBeans().isEmpty());
		assertNull(bean.getTestBeans().get(0).getName());
		assertTrue(ac.getBeanFactory().containsSingleton("testBean"));
		TestBean tb = (TestBean) ac.getBean("testBean");
		tb.setName("tb");
		assertSame("tb", bean.getTestBean().getName());
	}
