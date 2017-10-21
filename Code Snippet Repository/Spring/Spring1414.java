	@Test
	public void testCustomEditorConfigurerWithEditorAsClass() throws ParseException {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CustomEditorConfigurer cec = new CustomEditorConfigurer();
		Map<Class<?>, Class<? extends PropertyEditor>> editors = new HashMap<>();
		editors.put(Date.class, MyDateEditor.class);
		cec.setCustomEditors(editors);
		cec.postProcessBeanFactory(bf);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("date", "2.12.1975");
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setPropertyValues(pvs);
		bf.registerBeanDefinition("tb", bd);

		TestBean tb = (TestBean) bf.getBean("tb");
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
		assertEquals(df.parse("2.12.1975"), tb.getDate());
	}
