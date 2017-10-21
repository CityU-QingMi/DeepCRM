		@Override
		public Object[] removeLocalNaturalIdCrossReference(EntityPersister persister, Serializable id, Object[] state) {
			if ( !persister.hasNaturalIdentifier() ) {
				// nothing to do
				return null;
			}

			persister = locateProperPersister( persister );
			final Object[] naturalIdValues = getNaturalIdValues( state, persister );

			final Object[] localNaturalIdValues = naturalIdXrefDelegate.removeNaturalIdCrossReference( 
					persister, 
					id, 
					naturalIdValues 
			);

			return localNaturalIdValues != null ? localNaturalIdValues : naturalIdValues;
		}
