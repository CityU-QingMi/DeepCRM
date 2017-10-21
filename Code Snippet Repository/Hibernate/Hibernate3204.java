	private static void verifyNoIsVariantExists(
			Class containerClass,
			String propertyName,
			Method getMethod,
			String stemName) {
		// verify that the Class does not also define a method with the same stem name with 'is'
		try {
			final Method isMethod = containerClass.getDeclaredMethod( "is" + stemName );
			if ( isMethod.getAnnotation( Transient.class ) == null ) {
				// No such method should throw the caught exception.  So if we get here, there was
				// such a method.
				checkGetAndIsVariants( containerClass, propertyName, getMethod, isMethod );
			}
		}
		catch (NoSuchMethodException ignore) {
		}
	}
