		private NaturalIdResolutionCache(EntityPersister persister) {
			this.persister = persister;

			final int[] naturalIdPropertyIndexes = persister.getNaturalIdentifierProperties();
			naturalIdTypes = new Type[ naturalIdPropertyIndexes.length ];
			int i = 0;
			for ( int naturalIdPropertyIndex : naturalIdPropertyIndexes ) {
				naturalIdTypes[i++] = persister.getPropertyType( persister.getPropertyNames()[ naturalIdPropertyIndex ] );
			}
		}
