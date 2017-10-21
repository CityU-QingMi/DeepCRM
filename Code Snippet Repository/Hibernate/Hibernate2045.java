		@Override
		public Object[] extractNaturalIdValues(Object entity, EntityPersister persister) {
			if ( entity == null ) {
				throw new AssertionFailure( "Entity from which to extract natural id value(s) cannot be null" );
			}
			if ( persister == null ) {
				throw new AssertionFailure( "Persister to use in extracting natural id value(s) cannot be null" );
			}

			final int[] naturalIdentifierProperties = persister.getNaturalIdentifierProperties();
			final Object[] naturalIdValues = new Object[naturalIdentifierProperties.length];

			for ( int i = 0; i < naturalIdentifierProperties.length; i++ ) {
				naturalIdValues[i] = persister.getPropertyValue( entity, naturalIdentifierProperties[i] );
			}

			return naturalIdValues;
		}
