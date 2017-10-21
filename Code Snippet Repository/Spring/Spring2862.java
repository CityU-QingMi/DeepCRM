	@Test
	public void testPrototypeAdvisor() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(CONTEXT, CLASS));

		ITestBean bean1 = (ITestBean) bf.getBean("prototypeTestBeanProxy");
		ITestBean bean2 = (ITestBean) bf.getBean("prototypeTestBeanProxy");

		bean1.setAge(3);
		bean2.setAge(4);

		assertEquals(3, bean1.getAge());
		assertEquals(4, bean2.getAge());

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
