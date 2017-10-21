	@Test
	public void testComplexRemoteSlsb() throws Exception {
		BeanDefinition beanDefinition = this.beanFactory.getMergedBeanDefinition("complexRemoteEjb");
		assertEquals(SimpleRemoteStatelessSessionProxyFactoryBean.class.getName(), beanDefinition.getBeanClassName());
		assertPropertyValue(beanDefinition, "businessInterface", ITestBean.class.getName());
		assertPropertyValue(beanDefinition, "jndiName", "ejb/MyRemoteBean");
		assertPropertyValue(beanDefinition, "cacheHome", "true");
		assertPropertyValue(beanDefinition, "lookupHomeOnStartup", "true");
		assertPropertyValue(beanDefinition, "resourceRef", "true");
		assertPropertyValue(beanDefinition, "jndiEnvironment", "foo=bar");
		assertPropertyValue(beanDefinition, "homeInterface", "org.springframework.tests.sample.beans.ITestBean");
		assertPropertyValue(beanDefinition, "refreshHomeOnConnectFailure", "true");
		assertPropertyValue(beanDefinition, "cacheSessionBean", "true");
	}
