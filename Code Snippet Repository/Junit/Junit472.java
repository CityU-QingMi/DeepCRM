	@Test
	void passContextInformationToParameterResolverMethods() {
		anyTestMethodWithAtLeastOneParameter();
		ArgumentRecordingParameterResolver extension = new ArgumentRecordingParameterResolver();
		register(extension);

		invokeMethod();

		assertSame(extensionContext, extension.supportsArguments.extensionContext);
		assertEquals(0, extension.supportsArguments.parameterContext.getIndex());
		assertSame(instance, extension.supportsArguments.parameterContext.getTarget().get());
		assertSame(extensionContext, extension.resolveArguments.extensionContext);
		assertEquals(0, extension.resolveArguments.parameterContext.getIndex());
		assertSame(instance, extension.resolveArguments.parameterContext.getTarget().get());
	}
