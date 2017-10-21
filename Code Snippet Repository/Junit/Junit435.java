	@BeforeEach
	void before() throws Exception {
		extensionContext = mock(ExtensionContext.class);
		isClosed = false;

		context = new JupiterEngineExecutionContext(null, null).extend().withThrowableCollector(
			new ThrowableCollector()).withExtensionContext(extensionContext).build();

		Method testMethod = CustomStreamTestCase.class.getDeclaredMethod("customStream");
		descriptor = new TestFactoryTestDescriptor(UniqueId.forEngine("engine"), CustomStreamTestCase.class,
			testMethod);
		when(extensionContext.getTestMethod()).thenReturn(Optional.of(testMethod));
	}
