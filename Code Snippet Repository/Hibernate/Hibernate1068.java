	private ClassPool buildClassPool(JavassistEnhancementContext enhancementContext) {
		ClassPool classPool = new ClassPool( false ) {
			@Override
			public ClassLoader getClassLoader() {
				return enhancementContext.getLoadingClassLoader();
			}
		};

		ClassLoader loadingClassLoader = enhancementContext.getLoadingClassLoader();
		if ( loadingClassLoader != null ) {
			classPool.appendClassPath( new LoaderClassPath( loadingClassLoader ) );
		}
		return classPool;
	}
