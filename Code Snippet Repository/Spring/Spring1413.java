	@Test
	public void testCustomEditorConfigurerWithPropertyEditorRegistrar() throws ParseException {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CustomEditorConfigurer cec = new CustomEditorConfigurer();
		final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
		cec.setPropertyEditorRegistrars(new PropertyEditorRegistrar[] {
				new PropertyEditorRegistrar() {
					@Override
					public void registerCustomEditors(PropertyEditorRegistry registry) {
						registry.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
					}
				}});
		cec.postProcessBeanFactory(bf);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("date", "2.12.1975");
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setPropertyValues(pvs);
		bf.registerBeanDefinition("tb1", bd1);
		pvs = new MutablePropertyValues();
		pvs.add("someMap[myKey]", new TypedStringValue("2.12.1975", Date.class));
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setPropertyValues(pvs);
		bf.registerBeanDefinition("tb2", bd2);

		TestBean tb1 = (TestBean) bf.getBean("tb1");
		assertEquals(df.parse("2.12.1975"), tb1.getDate());
		TestBean tb2 = (TestBean) bf.getBean("tb2");
		assertEquals(df.parse("2.12.1975"), tb2.getSomeMap().get("myKey"));
	}
