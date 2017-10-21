	@Test
	public void testPropertyOverrideConfigurer() {
		BeanDefinition def1 = BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb1", def1);

		BeanDefinition def2 = BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).getBeanDefinition();
		factory.registerBeanDefinition("tb2", def2);

		PropertyOverrideConfigurer poc1;
		PropertyOverrideConfigurer poc2;

		{
			poc1 = new PropertyOverrideConfigurer();
			Properties props = new Properties();
			props.setProperty("tb1.age", "99");
			props.setProperty("tb2.name", "test");
			poc1.setProperties(props);
		}

		{
			poc2 = new PropertyOverrideConfigurer();
			Properties props = new Properties();
			props.setProperty("tb2.age", "99");
			props.setProperty("tb2.name", "test2");
			poc2.setProperties(props);
		}

		// emulate what happens when BFPPs are added to an application context: It's LIFO-based
		poc2.postProcessBeanFactory(factory);
		poc1.postProcessBeanFactory(factory);

		TestBean tb1 = (TestBean) factory.getBean("tb1");
		TestBean tb2 = (TestBean) factory.getBean("tb2");

		assertEquals(99, tb1.getAge());
		assertEquals(99, tb2.getAge());
		assertEquals(null, tb1.getName());
		assertEquals("test", tb2.getName());
	}
