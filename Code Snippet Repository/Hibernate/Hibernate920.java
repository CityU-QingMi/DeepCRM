		@Override
		public void doSecondPass(Map persistentClasses) throws org.hibernate.MappingException {
			bindCollectionTable();

			bindCollectionKey();
			bindCollectionIdentifier();
			bindCollectionIndex();
			bindCollectionElement();

			createBackReferences();

			collectionBinding.createAllKeys();

			if ( debugEnabled ) {
				log.debugf( "Mapped collection : " + getPluralAttributeSource().getAttributeRole().getFullPath() );
				log.debugf( "   + table -> " + getCollectionBinding().getTable().getName() );
				log.debugf( "   + key -> " + columns( getCollectionBinding().getKey() ) );
				if ( getCollectionBinding().isIndexed() ) {
					log.debugf( "   + index -> " + columns( ( (IndexedCollection) getCollectionBinding() ).getIndex() ) );
				}
				if ( getCollectionBinding().isOneToMany() ) {
					log.debugf( "   + one-to-many -> " + ( (OneToMany) getCollectionBinding().getElement() ).getReferencedEntityName() );
				}
				else {
					log.debugf( "   + element -> " + columns( getCollectionBinding().getElement() ) );
				}
			}
		}
