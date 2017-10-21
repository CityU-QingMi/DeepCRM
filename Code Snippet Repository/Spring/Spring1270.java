	@Test
	public void singleArgFactoryMethodInvokedWithNoArgs() {
		// calling a factory method that accepts arguments without any arguments emits an exception unlike cases
		// where a no-arg factory method is called with arguments. Adding this test just to document the difference
		assertExceptionMessageForMisconfiguredFactoryMethod(
				rootBeanDefinition(Foo.class).
						setFactoryMethod("singleArgFactory").getBeanDefinition(),
				"Error creating bean with name 'foo': " +
				"Unsatisfied dependency expressed through method 'singleArgFactory' parameter 0: " +
				"Ambiguous argument values for parameter of type [java.lang.String] - " +
				"did you specify the correct bean references as arguments?");
	}
