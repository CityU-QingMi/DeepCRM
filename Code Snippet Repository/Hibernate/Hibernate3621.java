		public void collectQuerySpaces(Collection<String> spaces) {
			for ( EntityPersister persister : alias2Persister.values() ) {
				Collections.addAll( spaces, (String[]) persister.getQuerySpaces() );
			}
			for ( CollectionPersister persister : alias2CollectionPersister.values() ) {
				final Type elementType = persister.getElementType();
				if ( elementType.isEntityType() && ! elementType.isAnyType() ) {
					final Joinable joinable = ( (EntityType) elementType ).getAssociatedJoinable( factory );
					Collections.addAll( spaces, (String[]) ( (EntityPersister) joinable ).getQuerySpaces() );
				}
			}
		}
