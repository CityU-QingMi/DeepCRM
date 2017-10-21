	@Test
	public void testExpressionInStringArray() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		BeanExpressionResolver beanExpressionResolver = mock(BeanExpressionResolver.class);
		when(beanExpressionResolver.evaluate(eq("#{foo}"), ArgumentMatchers.any(BeanExpressionContext.class)))
				.thenReturn("classpath:/org/springframework/beans/factory/xml/util.properties");
		bf.setBeanExpressionResolver(beanExpressionResolver);

		RootBeanDefinition rbd = new RootBeanDefinition(PropertiesFactoryBean.class);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("locations", new String[]{"#{foo}"});
		rbd.setPropertyValues(pvs);
		bf.registerBeanDefinition("myProperties", rbd);
		Properties properties = (Properties) bf.getBean("myProperties");
		assertEquals("bar", properties.getProperty("foo"));
	}
