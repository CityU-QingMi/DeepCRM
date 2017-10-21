		@Override
		public Object extractValue(Object owner) {
			if ( field == null ) {
				throw new AttributeExtractionException( "Attribute (field) " + name + " is not accessible" );
			}

			try {
				return field.get( owner );
			}
			catch ( IllegalAccessException e ) {
				throw new AttributeExtractionException(
						"Unable to access attribute (field): " + field.getDeclaringClass().getName() + "#" + name,
						e
				);
			}
		}
