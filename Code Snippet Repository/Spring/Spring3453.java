	@Test
	public void prototypeCreationIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		Assume.notLogging(factoryLog);
		GenericApplicationContext ac = new GenericApplicationContext();
		RootBeanDefinition rbd = new RootBeanDefinition(TestBean.class);
		rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		rbd.getConstructorArgumentValues().addGenericArgumentValue("#{systemProperties.name}");
		rbd.getPropertyValues().add("country", "#{systemProperties.country}");
		ac.registerBeanDefinition("test", rbd);
		ac.refresh();
		StopWatch sw = new StopWatch();
		sw.start("prototype");
		System.getProperties().put("name", "juergen");
		System.getProperties().put("country", "UK");
		try {
			for (int i = 0; i < 100000; i++) {
				TestBean tb = (TestBean) ac.getBean("test");
				assertEquals("juergen", tb.getName());
				assertEquals("UK", tb.getCountry());
			}
			sw.stop();
		}
		finally {
			System.getProperties().remove("country");
			System.getProperties().remove("name");
		}
		assertTrue("Prototype creation took too long: " + sw.getTotalTimeMillis(), sw.getTotalTimeMillis() < 6000);
	}
