	@Test
	public void testPrototypeInterceptorSingletonTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(CONTEXT, CLASS));

		ITestBean bean1 = (ITestBean) bf.getBean("prototypeTestBeanProxySingletonTarget");
		ITestBean bean2 = (ITestBean) bf.getBean("prototypeTestBeanProxySingletonTarget");

		bean1.setAge(1);
		bean2.setAge(2);

		assertEquals(2, bean1.getAge());

		((Lockable) bean1).lock();

		try {
			bean1.setAge(5);
			fail("expected LockedException");
		}
		catch (LockedException ex) {
			// expected
		}

		try {
			bean2.setAge(6);
		}
		catch (LockedException ex) {
			fail("did not expect LockedException");
		}
	}
