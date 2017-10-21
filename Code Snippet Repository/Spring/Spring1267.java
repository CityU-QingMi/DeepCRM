	@Test
	public void noArgFactoryMethodInvokedWithTwoArgs() {
		assertExceptionMessageForMisconfiguredFactoryMethod(
				rootBeanDefinition(Foo.class)
					.setFactoryMethod("noArgFactory")
					.addConstructorArgValue("bogusArg1")
					.addConstructorArgValue("bogusArg2".getBytes()).getBeanDefinition(),
				"Error creating bean with name 'foo': No matching factory method found: factory method 'noArgFactory(String,byte[])'. " +
				"Check that a method with the specified name and arguments exists and that it is static.");
	}
