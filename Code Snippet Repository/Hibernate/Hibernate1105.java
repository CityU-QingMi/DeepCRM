	private static CtMethod getterOrNull(CtClass containerClass, String propertyName) {
		for ( CtMethod method : containerClass.getDeclaredMethods() ) {
			try {
				// if the method has parameters, skip it
				if ( method.isEmpty() || method.getParameterTypes().length != 0 ) {
					continue;
				}
			}
			catch (NotFoundException e) {
				continue;
			}

			final String methodName = method.getName();

			// try "get"
			if ( methodName.startsWith( "get" ) ) {
				String testStdMethod = Introspector.decapitalize( methodName.substring( 3 ) );
				String testOldMethod = methodName.substring( 3 );
				if ( testStdMethod.equals( propertyName ) || testOldMethod.equals( propertyName ) ) {
					return method;
				}
			}

			// if not "get", then try "is"
			if ( methodName.startsWith( "is" ) ) {
				String testStdMethod = Introspector.decapitalize( methodName.substring( 2 ) );
				String testOldMethod = methodName.substring( 2 );
				if ( testStdMethod.equals( propertyName ) || testOldMethod.equals( propertyName ) ) {
					return method;
				}
			}
		}
		return null;
	}
