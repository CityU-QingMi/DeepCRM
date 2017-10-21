	private Object[] getNaturalIdValues(Object[] state, EntityPersister persister) {
		final int[] naturalIdPropertyIndexes = persister.getNaturalIdentifierProperties();
		final Object[] naturalIdValues = new Object[naturalIdPropertyIndexes.length];

		for ( int i = 0; i < naturalIdPropertyIndexes.length; i++ ) {
			naturalIdValues[i] = state[naturalIdPropertyIndexes[i]];
		}

		return naturalIdValues;
	}
