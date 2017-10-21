	public void indexMappingDocument(MappingDocument mappingDocument) {
		log.tracef( "Indexing mapping document [%s] for purpose of building entity hierarchy ordering", mappingDocument.getOrigin() );
		final JaxbHbmHibernateMapping mappingBinding = mappingDocument.getDocumentRoot();

		// iterate all root class definitions at the hibernate-mapping level
		for ( JaxbHbmRootEntityType jaxbRootEntity : mappingBinding.getClazz() ) {

			// we can immediately handle <class/> elements in terms of creating the hierarchy entry
			final RootEntitySourceImpl rootEntitySource = new RootEntitySourceImpl( mappingDocument, jaxbRootEntity );
			entitySourceByNameMap.put( rootEntitySource.getEntityNamingSource().getEntityName(), rootEntitySource );

			final EntityHierarchySourceImpl hierarchy = new EntityHierarchySourceImpl( rootEntitySource );
			entityHierarchyList.add( hierarchy );

			linkAnyWaiting( mappingDocument, rootEntitySource );

			// process any of its nested sub-entity definitions
			processRootEntitySubEntityElements( mappingDocument, jaxbRootEntity, rootEntitySource );
		}

		// iterate all discriminator-based subclass definitions at the hibernate-mapping level
		for ( JaxbHbmDiscriminatorSubclassEntityType discriminatorSubclassEntityBinding : mappingBinding.getSubclass() ) {
			processTopLevelSubClassBinding( mappingDocument, discriminatorSubclassEntityBinding );
		}

		// iterate all joined-subclass definitions at the hibernate-mapping level
		for ( JaxbHbmJoinedSubclassEntityType joinedSubclassEntityBinding : mappingBinding.getJoinedSubclass() ) {
			processTopLevelSubClassBinding( mappingDocument, joinedSubclassEntityBinding );
		}

		// iterate all union-subclass definitions at the hibernate-mapping level
		for ( JaxbHbmUnionSubclassEntityType unionSubclassEntityBinding : mappingBinding.getUnionSubclass() ) {
			processTopLevelSubClassBinding( mappingDocument, unionSubclassEntityBinding );
		}
	}
