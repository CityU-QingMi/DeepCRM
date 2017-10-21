	private ExtensionContext buildExtensionContext(String methodName) {
		Class<?> testClass = SpringTestCase.class;
		Method method = ReflectionUtils.findMethod(getClass(), methodName);
		Store store = mock(Store.class);
		when(store.getOrComputeIfAbsent(any(), any(), any())).thenReturn(new TestContextManager(testClass));

		ExtensionContext extensionContext = mock(ExtensionContext.class);
		when(extensionContext.getTestClass()).thenReturn(Optional.of(testClass));
		when(extensionContext.getElement()).thenReturn(Optional.of(method));
		when(extensionContext.getStore(any())).thenReturn(store);
		return extensionContext;
	}
