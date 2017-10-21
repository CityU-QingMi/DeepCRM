	private static Class<?>[] enhanceTestClass(Class<?> klass) throws ClassNotFoundException {
		String packageName = klass.getPackage().getName();
		List<Class<?>> classList = new ArrayList<>();

		try {
			if ( klass.isAnnotationPresent( CustomEnhancementContext.class ) ) {
				for ( Class<? extends EnhancementContext> contextClass : klass.getAnnotation( CustomEnhancementContext.class ).value() ) {
					EnhancementContext enhancementContextInstance = contextClass.getConstructor().newInstance();
					classList.add( getEnhancerClassLoader( enhancementContextInstance, packageName ).loadClass( klass.getName() ) );
				}
			}
			else {
				classList.add( getEnhancerClassLoader( new EnhancerTestContext(), packageName ).loadClass( klass.getName() ) );
			}
		}
		catch ( IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e ) {
			// This is unlikely, but if happens throw runtime exception to fail the test
			throw new RuntimeException( e );
		}
		return classList.toArray( new Class<?>[]{} );
	}
