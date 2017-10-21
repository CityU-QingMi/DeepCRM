	@Test
	public void testPreferencesPlaceholderConfigurerWithCustomTreePaths() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${myName}")
				.addPropertyValue("age", "${myAge}")
				.addPropertyValue("touchy", "${myTouchy}")
				.getBeanDefinition());

		PreferencesPlaceholderConfigurer ppc = new PreferencesPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("myAge", "99");
		ppc.setProperties(props);
		ppc.setSystemTreePath("mySystemPath");
		ppc.setUserTreePath("myUserPath");
		Preferences.systemRoot().node("mySystemPath").put("myName", "myNameValue");
		Preferences.systemRoot().node("mySystemPath").put("myTouchy", "myTouchyValue");
		Preferences.userRoot().node("myUserPath").put("myTouchy", "myOtherTouchyValue");
		ppc.afterPropertiesSet();
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("myNameValue", tb.getName());
		assertEquals(99, tb.getAge());
		assertEquals("myOtherTouchyValue", tb.getTouchy());
		Preferences.userRoot().node("myUserPath").remove("myTouchy");
		Preferences.systemRoot().node("mySystemPath").remove("myTouchy");
		Preferences.systemRoot().node("mySystemPath").remove("myName");
	}
