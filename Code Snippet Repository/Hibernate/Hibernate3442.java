		@Override
		public Object extractValue(Object owner) {
			if ( method == null ) {
				throw new AttributeExtractionException( "Attribute (method) " + name + " is not accessible" );
			}

			try {
				return method.invoke( owner );
			}
			catch ( IllegalAccessException e ) {
				throw new AttributeExtractionException(
						"Unable to access attribute (method): " + method.getDeclaringClass().getName() + "#" + name,
						e
				);
			}
			catch ( InvocationTargetException e ) {
				throw new AttributeExtractionException(
						"Unable to access attribute (method): " + method.getDeclaringClass().getName() + "#" + name,
						e.getCause()
				);
			}
		}
