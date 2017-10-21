	@Override
	protected Statement methodBlock(FrameworkMethod method) {
		log.info( Test.class.getSimpleName() + ": " + method.getName() );

		final Statement originalMethodBlock = super.methodBlock( method );
		final ExtendedFrameworkMethod extendedFrameworkMethod = (ExtendedFrameworkMethod) method;
		return new FailureExpectedHandler(
				originalMethodBlock,
				testClassMetadata,
				extendedFrameworkMethod,
				testInstance
		);
	}
