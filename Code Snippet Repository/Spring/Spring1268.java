	@Test
	public void noArgFactoryMethodInvokedWithTwoArgsAndTypesSpecified() {
		RootBeanDefinition def = new RootBeanDefinition(Foo.class);
		def.setFactoryMethodName("noArgFactory");
		ConstructorArgumentValues cav = new ConstructorArgumentValues();
		cav.addIndexedArgumentValue(0, "bogusArg1", CharSequence.class.getName());
		cav.addIndexedArgumentValue(1, "bogusArg2".getBytes());
		def.setConstructorArgumentValues(cav);

		assertExceptionMessageForMisconfiguredFactoryMethod(def,
				"Error creating bean with name 'foo': No matching factory method found: factory method 'noArgFactory(CharSequence,byte[])'. " +
				"Check that a method with the specified name and arguments exists and that it is static.");
	}
