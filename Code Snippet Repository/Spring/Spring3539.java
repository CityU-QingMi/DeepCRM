	@Ignore
	@Test
	public void testPropertyPlaceholderConfigurerWithAutowireByType() {
//		StaticApplicationContext ac = new StaticApplicationContext();
//		MutablePropertyValues pvs = new MutablePropertyValues();
//		pvs.addPropertyValue("touchy", "${test}");
//		ac.registerSingleton("tb", TestBean.class, pvs);
//		pvs = new MutablePropertyValues();
//		pvs.addPropertyValue("target", new RuntimeBeanReference("tb"));
//		// uncomment when fixing this test
//		// ac.registerSingleton("tbProxy", org.springframework.aop.framework.ProxyFactoryBean.class, pvs);
//		pvs = new MutablePropertyValues();
//		Properties props = new Properties();
//		props.put("test", "mytest");
//		pvs.addPropertyValue("properties", new Properties(props));
//		RootBeanDefinition ppcDef = new RootBeanDefinition(PropertyPlaceholderConfigurer.class, pvs);
//		ppcDef.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
//		ac.registerBeanDefinition("configurer", ppcDef);
//		ac.refresh();
//		TestBean tb = (TestBean) ac.getBean("tb");
//		assertEquals("mytest", tb.getTouchy());
	}
