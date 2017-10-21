	@Test
	public void twoPlacholderConfigurers_withConflictingSettings() {
		String P2 = "p2";
		String P2_LOCAL_PROPS_VAL = "p2LocalPropsVal";
		String P2_SYSTEM_PROPS_VAL = "p2SystemPropsVal";
		String P2_SYSTEM_ENV_VAL = "p2SystemEnvVal";

		AbstractBeanDefinition p2BeanDef = rootBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${" + P1 + "}")
				.addPropertyValue("country", "${" + P2 + "}")
				.getBeanDefinition();

		bf.registerBeanDefinition("p1Bean", p1BeanDef);
		bf.registerBeanDefinition("p2Bean", p2BeanDef);

		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.postProcessBeanFactory(bf);

		System.setProperty(P2, P2_SYSTEM_PROPS_VAL);
		getModifiableSystemEnvironment().put(P2, P2_SYSTEM_ENV_VAL);
		Properties ppc2Properties = new Properties();
		ppc2Properties.put(P2, P2_LOCAL_PROPS_VAL);

		PropertyPlaceholderConfigurer ppc2 = new PropertyPlaceholderConfigurer();
		ppc2.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		ppc2.setProperties(ppc2Properties);

		ppc2Properties = new Properties();
		ppc2Properties.setProperty(P2, P2_LOCAL_PROPS_VAL);
		ppc2.postProcessBeanFactory(bf);

		TestBean p1Bean = bf.getBean("p1Bean", TestBean.class);
		assertThat(p1Bean.getName(), equalTo(P1_LOCAL_PROPS_VAL));

		TestBean p2Bean = bf.getBean("p2Bean", TestBean.class);
		assertThat(p2Bean.getName(), equalTo(P1_LOCAL_PROPS_VAL));
		assertThat(p2Bean.getCountry(), equalTo(P2_SYSTEM_PROPS_VAL));

		System.clearProperty(P2);
		getModifiableSystemEnvironment().remove(P2);
	}
