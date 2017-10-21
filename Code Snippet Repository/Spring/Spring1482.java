	@Test
	public void testRequiresListableBeanFactoryAndChokesOnAnythingElse() throws Exception {
		BeanFactory beanFactory = mock(BeanFactory.class);
		try {
			ServiceLocatorFactoryBean factory = new ServiceLocatorFactoryBean();
			factory.setBeanFactory(beanFactory);
		}
		catch (FatalBeanException ex) {
			// expected
		}
	}
