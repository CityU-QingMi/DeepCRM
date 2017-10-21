	private Class determineKeyClass(XClass keyXClass) {
		if ( keyXClass != null ) {
			try {
				return getContext().getBuildingOptions().getReflectionManager().toClass( keyXClass );
			}
			catch (Exception e) {
				log.debugf(
						"Unable to resolve XClass [%s] to Class for collection key [%s]",
						keyXClass.getName(),
						collection.getRole()
				);
			}
		}

		final IndexedCollection indexedCollection = (IndexedCollection) collection;
		if ( indexedCollection.getIndex() != null ) {
			if ( indexedCollection.getIndex().getType() != null ) {
				return indexedCollection.getIndex().getType().getReturnedClass();
			}
		}

		// currently this is called from paths where the element type really should be known,
		// so log the fact that we could not resolve the collection element info
		log.debugf(
				"Unable to resolve key information for collection [%s]",
				collection.getRole()
		);
		return null;
	}
