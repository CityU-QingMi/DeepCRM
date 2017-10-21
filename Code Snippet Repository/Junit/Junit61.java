	@Override
	protected void invokeTestMethod(JupiterEngineExecutionContext context, DynamicTestExecutor dynamicTestExecutor) {
		ExtensionContext extensionContext = context.getExtensionContext();

		context.getThrowableCollector().execute(() -> {
			Object instance = extensionContext.getRequiredTestInstance();
			Object testFactoryMethodResult = executableInvoker.invoke(getTestMethod(), instance, extensionContext,
				context.getExtensionRegistry());
			TestSource source = getSource().orElseThrow(
				() -> new JUnitException("Illegal state: TestSource must be present"));
			try (Stream<DynamicNode> dynamicNodeStream = toDynamicNodeStream(testFactoryMethodResult)) {
				int index = 1;
				Iterator<DynamicNode> iterator = dynamicNodeStream.iterator();
				while (iterator.hasNext()) {
					DynamicNode dynamicNode = iterator.next();
					JupiterTestDescriptor descriptor = createDynamicDescriptor(this, dynamicNode, index++, source);
					dynamicTestExecutor.execute(descriptor);
				}
			}
			catch (ClassCastException ex) {
				throw invalidReturnTypeException(ex);
			}
		});
	}
