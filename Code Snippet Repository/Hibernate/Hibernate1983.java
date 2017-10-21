		public CachedNaturalId(EntityPersister persister, Object[] values) {
			this.persister = persister;
			this.values = values;

			final int prime = 31;
			int hashCodeCalculation = 1;
			hashCodeCalculation = prime * hashCodeCalculation + persister.hashCode();

			final int[] naturalIdPropertyIndexes = persister.getNaturalIdentifierProperties();
			naturalIdTypes = new Type[ naturalIdPropertyIndexes.length ];
			int i = 0;
			for ( int naturalIdPropertyIndex : naturalIdPropertyIndexes ) {
				final Type type = persister.getPropertyType( persister.getPropertyNames()[ naturalIdPropertyIndex ] );
				naturalIdTypes[i] = type;
				final int elementHashCode = values[i] == null ? 0 :type.getHashCode( values[i], persister.getFactory() );
				hashCodeCalculation = prime * hashCodeCalculation + elementHashCode;
				i++;
			}

			this.hashCode = hashCodeCalculation;
		}
