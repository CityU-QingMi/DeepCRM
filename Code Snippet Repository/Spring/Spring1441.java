	@Test
	public void setSystemSystemPropertiesMode_toOverride_andSetSearchSystemEnvironment_toFalse() {
		registerWithGeneratedName(p1BeanDef, bf);
		System.clearProperty(P1); // will now fall all the way back to system environment
		ppc.setSearchSystemEnvironment(false);
		ppc.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		ppc.postProcessBeanFactory(bf);
		TestBean bean = bf.getBean(TestBean.class);
		assertThat(bean.getName(), equalTo(P1_LOCAL_PROPS_VAL)); // has to resort to local props
	}
