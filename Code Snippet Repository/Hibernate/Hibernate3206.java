	private static void verifyNoGetVariantExists(
			Class containerClass,
			String propertyName,
			Method isMethod,
			String stemName) {
		// verify that the Class does not also define a method with the same stem name with 'is'
		try {
			final Method getMethod = containerClass.getDeclaredMethod( "get" + stemName );
			// No such method should throw the caught exception.  So if we get here, there was
			// such a method.
			if ( getMethod.getAnnotation( Transient.class ) == null ) {
				checkGetAndIsVariants( containerClass, propertyName, getMethod, isMethod );
			}
		}
		catch (NoSuchMethodException ignore) {
		}
	}
