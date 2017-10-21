	@Test
	@Ignore
	public void testPrototypeCreationWithConstructorArgumentsIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		Assume.notLogging(factoryLog);
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition rbd = new RootBeanDefinition(TestBean.class);
		rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		rbd.getConstructorArgumentValues().addGenericArgumentValue("juergen");
		rbd.getConstructorArgumentValues().addGenericArgumentValue("99");
		lbf.registerBeanDefinition("test", rbd);
		StopWatch sw = new StopWatch();
		sw.start("prototype");
		for (int i = 0; i < 100000; i++) {
			TestBean tb = (TestBean) lbf.getBean("test");
			assertEquals("juergen", tb.getName());
			assertEquals(99, tb.getAge());
		}
		sw.stop();
		// System.out.println(sw.getTotalTimeMillis());
		assertTrue("Prototype creation took too long: " + sw.getTotalTimeMillis(), sw.getTotalTimeMillis() < 3000);
	}
