	@SuppressWarnings("")
	@Override
	public Object intercept(Object instance, Method method, Object[] arguments) throws Exception {
		final String name = method.getName();
		if ( "toString".equals( name ) ) {
			return proxiedClassName + "@" + System.identityHashCode( instance );
		}
		else if ( "equals".equals( name ) ) {
			return proxiedObject == instance;
		}
		else if ( "hashCode".equals( name ) ) {
			return System.identityHashCode( instance );
		}

		final boolean hasGetterSignature = method.getParameterCount() == 0
				&& method.getReturnType() != null;
		final boolean hasSetterSignature = method.getParameterCount() == 1
				&& ( method.getReturnType() == null || method.getReturnType() == void.class );

		if ( name.startsWith( "get" ) && hasGetterSignature ) {
			final String propName = name.substring( 3 );
			return data.get( propName );
		}
		else if ( name.startsWith( "is" ) && hasGetterSignature ) {
			final String propName = name.substring( 2 );
			return data.get( propName );
		}
		else if ( name.startsWith( "set" ) && hasSetterSignature ) {
			final String propName = name.substring( 3 );
			data.put( propName, arguments[0] );
			return null;
		}
		else {
			// todo : what else to do here?
			return null;
		}
	}
