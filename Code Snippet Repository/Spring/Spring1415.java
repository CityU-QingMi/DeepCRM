	@Test
	public void testCustomEditorConfigurerWithRequiredTypeArray() throws ParseException {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CustomEditorConfigurer cec = new CustomEditorConfigurer();
		Map<Class<?>, Class<? extends PropertyEditor>> editors = new HashMap<>();
		editors.put(String[].class, MyTestEditor.class);
		cec.setCustomEditors(editors);
		cec.postProcessBeanFactory(bf);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("stringArray", "xxx");
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setPropertyValues(pvs);
		bf.registerBeanDefinition("tb", bd);

		TestBean tb = (TestBean) bf.getBean("tb");
		assertTrue(tb.getStringArray() != null && tb.getStringArray().length == 1);
		assertEquals("test", tb.getStringArray()[0]);
	}
