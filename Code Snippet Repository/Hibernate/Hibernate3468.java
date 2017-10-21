	protected void postInstantiate() {
		Loadable[] persisters = getEntityPersisters();
		String[] suffixes = getSuffixes();
		descriptors = new EntityAliases[persisters.length];
		for ( int i=0; i<descriptors.length; i++ ) {
			descriptors[i] = new DefaultEntityAliases( persisters[i], suffixes[i] );
		}

		CollectionPersister[] collectionPersisters = getCollectionPersisters();
		List bagRoles = null;
		if ( collectionPersisters != null ) {
			String[] collectionSuffixes = getCollectionSuffixes();
			collectionDescriptors = new CollectionAliases[collectionPersisters.length];
			for ( int i = 0; i < collectionPersisters.length; i++ ) {
				if ( isBag( collectionPersisters[i] ) ) {
					if ( bagRoles == null ) {
						bagRoles = new ArrayList();
					}
					bagRoles.add( collectionPersisters[i].getRole() );
				}
				collectionDescriptors[i] = new GeneratedCollectionAliases(
						collectionPersisters[i],
						collectionSuffixes[i]
					);
			}
		}
		else {
			collectionDescriptors = null;
		}
		if ( bagRoles != null && bagRoles.size() > 1 ) {
			throw new MultipleBagFetchException( bagRoles );
		}
	}
