	@Override
	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				final ClassLoader originalTCCL = Thread.currentThread().getContextClassLoader();
				final ClassLoader isolatedClassLoader = provider.buildIsolatedClassLoader();

				log.infof( "Overriding TCCL [%s] -> [%s]", originalTCCL, isolatedClassLoader );

				Thread.currentThread().setContextClassLoader( isolatedClassLoader );

				try {
					base.evaluate();
				}
				finally {
					assert Thread.currentThread().getContextClassLoader() == isolatedClassLoader;
					log.infof( "Reverting TCCL [%s] -> [%s]", isolatedClassLoader, originalTCCL );

					Thread.currentThread().setContextClassLoader( originalTCCL );
					provider.releaseIsolatedClassLoader( isolatedClassLoader );
				}
			}
		};
	}
