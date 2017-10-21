		@SuppressWarnings("")
		public Object invoke(
				Object object,
				Method method,
				Method method1,
				Object[] args) throws Exception {
			final String name = method.getName();
			if ( "toString".equals( name ) ) {
				return proxiedClassName + "@" + System.identityHashCode( object );
			}
			else if ( "equals".equals( name ) ) {
				return proxiedObject == object;
			}
			else if ( "hashCode".equals( name ) ) {
				return System.identityHashCode( object );
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
			else if ( name.startsWith( "set" ) && hasSetterSignature) {
				final String propName = name.substring( 3 );
				data.put( propName, args[0] );
				return null;
			}
			else {
				// todo : what else to do here?
				return null;
			}
		}
