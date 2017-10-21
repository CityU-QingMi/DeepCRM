	@Test
	public void testPropertyOverrideConfigurerWithIgnoreInvalidKeys() {
		factory.registerBeanDefinition("tb1", genericBeanDefinition(TestBean.class).getBeanDefinition());
		factory.registerBeanDefinition("tb2", genericBeanDefinition(TestBean.class).getBeanDefinition());

		{
			PropertyOverrideConfigurer poc = new PropertyOverrideConfigurer();
			Properties props = new Properties();
			props.setProperty("tb2.age", "99");
			props.setProperty("tb2.name", "test2");
			poc.setProperties(props);
			poc.setOrder(0); // won't actually do anything since we're not processing through an app ctx
			poc.postProcessBeanFactory(factory);
		}
		{
			PropertyOverrideConfigurer poc = new PropertyOverrideConfigurer();
			poc.setIgnoreInvalidKeys(true);
			Properties props = new Properties();
			props.setProperty("argh", "hgra");
			props.setProperty("tb1.age", "99");
			props.setProperty("tb2.name", "test");
			poc.setProperties(props);
			poc.postProcessBeanFactory(factory);
		}

		TestBean tb1 = (TestBean) factory.getBean("tb1");
		TestBean tb2 = (TestBean) factory.getBean("tb2");
		assertEquals(99, tb1.getAge());
		assertEquals(99, tb2.getAge());
		assertEquals(null, tb1.getName());
		assertEquals("test", tb2.getName());
	}
