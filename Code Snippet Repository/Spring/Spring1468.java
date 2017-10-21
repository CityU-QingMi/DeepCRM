	@Test
	public void testPreferencesPlaceholderConfigurerWithPathInPlaceholder() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${mypath/myName}")
				.addPropertyValue("age", "${myAge}")
				.addPropertyValue("touchy", "${myotherpath/myTouchy}")
				.getBeanDefinition());

		PreferencesPlaceholderConfigurer ppc = new PreferencesPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("myAge", "99");
		ppc.setProperties(props);
		ppc.setSystemTreePath("mySystemPath");
		ppc.setUserTreePath("myUserPath");
		Preferences.systemRoot().node("mySystemPath").node("mypath").put("myName", "myNameValue");
		Preferences.systemRoot().node("mySystemPath/myotherpath").put("myTouchy", "myTouchyValue");
		Preferences.userRoot().node("myUserPath/myotherpath").put("myTouchy", "myOtherTouchyValue");
		ppc.afterPropertiesSet();
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("myNameValue", tb.getName());
		assertEquals(99, tb.getAge());
		assertEquals("myOtherTouchyValue", tb.getTouchy());
		Preferences.userRoot().node("myUserPath/myotherpath").remove("myTouchy");
		Preferences.systemRoot().node("mySystemPath/myotherpath").remove("myTouchy");
		Preferences.systemRoot().node("mySystemPath/mypath").remove("myName");
	}
