	@Test
	public void testCustomTypeConverterWithBeanReference() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		lbf.setTypeConverter(new CustomTypeConverter(nf));
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("myFloat", new RuntimeBeanReference("myFloat"));
		ConstructorArgumentValues cav = new ConstructorArgumentValues();
		cav.addIndexedArgumentValue(0, "myName");
		cav.addIndexedArgumentValue(1, "myAge");
		lbf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class, cav, pvs));
		lbf.registerSingleton("myFloat", "1,1");
		TestBean testBean = (TestBean) lbf.getBean("testBean");
		assertEquals("myName", testBean.getName());
		assertEquals(5, testBean.getAge());
		assertTrue(testBean.getMyFloat().floatValue() == 1.1f);
	}
