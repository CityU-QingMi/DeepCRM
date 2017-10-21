	@Test
	public void testDoesNotComplainWhenTargetBeanNameRefersToSingleton() throws Exception {
		final String targetBeanName = "singleton";
		final String expectedSingleton = "Alicia Keys";

		BeanFactory beanFactory = mock(BeanFactory.class);
		given(beanFactory.getBean(targetBeanName)).willReturn(expectedSingleton);

		ObjectFactoryCreatingFactoryBean factory = new ObjectFactoryCreatingFactoryBean();
		factory.setTargetBeanName(targetBeanName);
		factory.setBeanFactory(beanFactory);
		factory.afterPropertiesSet();
		ObjectFactory<?> objectFactory = factory.getObject();
		Object actualSingleton = objectFactory.getObject();
		assertSame(expectedSingleton, actualSingleton);
	}
