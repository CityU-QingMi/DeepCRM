	public AbstractRowReader(ReaderCollector readerCollector) {
		if ( CollectionHelper.isNotEmpty( readerCollector.getEntityReferenceInitializers() ) ) {
			entityReferenceInitializers = new ArrayList<EntityReferenceInitializer>(
					readerCollector.getEntityReferenceInitializers()
			);
			entityInitializerByEntityReference =
					new HashMap<EntityReference, EntityReferenceInitializer>( entityReferenceInitializers.size() );
			for ( EntityReferenceInitializer entityReferenceInitializer : entityReferenceInitializers ) {
				entityInitializerByEntityReference.put(
						entityReferenceInitializer.getEntityReference(),
						entityReferenceInitializer
				);
			}
		}
		else {
			entityReferenceInitializers = Collections.<EntityReferenceInitializer>emptyList();
			entityInitializerByEntityReference = Collections.<EntityReference,EntityReferenceInitializer>emptyMap();
		}
		this.arrayReferenceInitializers = CollectionHelper.isNotEmpty( readerCollector.getArrayReferenceInitializers() )
				? new ArrayList<CollectionReferenceInitializer>( readerCollector.getArrayReferenceInitializers() )
				: Collections.<CollectionReferenceInitializer>emptyList();
		this.collectionReferenceInitializers =
				CollectionHelper.isNotEmpty ( readerCollector.getNonArrayCollectionReferenceInitializers() )
				? new ArrayList<CollectionReferenceInitializer>( readerCollector.getNonArrayCollectionReferenceInitializers() )
				: Collections.<CollectionReferenceInitializer>emptyList();
	}
