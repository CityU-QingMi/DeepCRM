	@Test
	public void testPreferencesPlaceholderConfigurer() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${myName}")
				.addPropertyValue("age", "${myAge}")
				.addPropertyValue("touchy", "${myTouchy}")
				.getBeanDefinition());

		PreferencesPlaceholderConfigurer ppc = new PreferencesPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("myAge", "99");
		ppc.setProperties(props);
		Preferences.systemRoot().put("myName", "myNameValue");
		Preferences.systemRoot().put("myTouchy", "myTouchyValue");
		Preferences.userRoot().put("myTouchy", "myOtherTouchyValue");
		ppc.afterPropertiesSet();
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("myNameValue", tb.getName());
		assertEquals(99, tb.getAge());
		assertEquals("myOtherTouchyValue", tb.getTouchy());
		Preferences.userRoot().remove("myTouchy");
		Preferences.systemRoot().remove("myTouchy");
		Preferences.systemRoot().remove("myName");
	}
