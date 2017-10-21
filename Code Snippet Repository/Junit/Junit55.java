	@Override
	public JupiterEngineExecutionContext execute(JupiterEngineExecutionContext context,
			DynamicTestExecutor dynamicTestExecutor) throws Exception {
		AtomicInteger index = new AtomicInteger(1);
		try (Stream<? extends DynamicNode> children = dynamicContainer.getChildren()) {
			// @formatter:off
			children.peek(child -> Preconditions.notNull(child, "individual dynamic node must not be null"))
					.map(child -> toDynamicDescriptor(index.getAndIncrement(), child))
					.forEachOrdered(dynamicTestExecutor::execute);
			// @formatter:on
		}
		return context;
	}
