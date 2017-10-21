		@Override
		public Object[] extractNaturalIdValues(Object[] state, EntityPersister persister) {
			final int[] naturalIdPropertyIndexes = persister.getNaturalIdentifierProperties();
			if ( state.length == naturalIdPropertyIndexes.length ) {
				return state;
			}

			final Object[] naturalIdValues = new Object[naturalIdPropertyIndexes.length];
			for ( int i = 0; i < naturalIdPropertyIndexes.length; i++ ) {
				naturalIdValues[i] = state[naturalIdPropertyIndexes[i]];
			}
			return naturalIdValues;
		}
