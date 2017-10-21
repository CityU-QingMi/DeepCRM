	private void assertExceptionMessageForMisconfiguredFactoryMethod(BeanDefinition bd, String expectedMessage) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("foo", bd);

		try {
			factory.preInstantiateSingletons();
			fail("should have failed with BeanCreationException due to incorrectly invoked factory method");
		}
		catch (BeanCreationException ex) {
			assertThat(ex.getMessage(), equalTo(expectedMessage));
		}
	}
