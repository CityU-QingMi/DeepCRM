		private AttributeAccess buildAttributeAccess(final String attributeName) {
			final PrivilegedAction<AttributeAccess> action = new PrivilegedAction<AttributeAccess>() {
				@Override
				public AttributeAccess run() {
					for ( Class clazz : classHierarchy ) {
						try {
							final Field field = clazz.getDeclaredField( attributeName );
							if ( field != null ) {
								return new FieldAttributeAccess( field );
							}
						}
						catch ( NoSuchFieldException e ) {
							final Method method = getMethod( clazz, attributeName );
							if ( method != null ) {
								return new MethodAttributeAccess( attributeName, method );
							}
						}
					}
					//we could not find any match
					return new NoSuchAttributeAccess( specifiedClass, attributeName );
				}
			};
			return System.getSecurityManager() != null ? AccessController.doPrivileged( action ) : action.run();
		}
