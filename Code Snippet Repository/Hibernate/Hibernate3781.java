		@Override
		public void add(CollectionReferenceInitializer collectionReferenceInitializer) {
			if ( collectionReferenceInitializer.getCollectionReference().getCollectionPersister().isArray() ) {
				if ( arrayReferenceInitializers == null ) {
					arrayReferenceInitializers = new ArrayList<CollectionReferenceInitializer>();
				}
				arrayReferenceInitializers.add( collectionReferenceInitializer );
			}
			else {
				if ( collectionReferenceInitializers == null ) {
					collectionReferenceInitializers = new ArrayList<CollectionReferenceInitializer>();
				}
				collectionReferenceInitializers.add( collectionReferenceInitializer );
			}
		}
