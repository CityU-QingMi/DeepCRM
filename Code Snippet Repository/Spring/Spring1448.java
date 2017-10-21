	@Test
	public void testPropertyOverrideConfigurerWithInvalidKey() {
		factory.registerBeanDefinition("tb1", genericBeanDefinition(TestBean.class).getBeanDefinition());
		factory.registerBeanDefinition("tb2", genericBeanDefinition(TestBean.class).getBeanDefinition());

		{
			PropertyOverrideConfigurer poc = new PropertyOverrideConfigurer();
			poc.setIgnoreInvalidKeys(true);
			Properties props = new Properties();
			props.setProperty("argh", "hgra");
			props.setProperty("tb2.name", "test");
			props.setProperty("tb2.nam", "test");
			props.setProperty("tb3.name", "test");
			poc.setProperties(props);
			poc.postProcessBeanFactory(factory);
			assertEquals("test", factory.getBean("tb2", TestBean.class).getName());
		}
		{
			PropertyOverrideConfigurer poc = new PropertyOverrideConfigurer();
			Properties props = new Properties();
			props.setProperty("argh", "hgra");
			props.setProperty("tb2.age", "99");
			props.setProperty("tb2.name", "test2");
			poc.setProperties(props);
			poc.setOrder(0); // won't actually do anything since we're not processing through an app ctx
			try {
				poc.postProcessBeanFactory(factory);
			}
			catch (BeanInitializationException ex) {
				// prove that the processor chokes on the invalid key
				assertTrue(ex.getMessage().toLowerCase().contains("argh"));
			}
		}
	}
