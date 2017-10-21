		public void associationProcessed(OuterJoinableAssociation oja, int position) {
			associationsByAlias.put( oja.getRhsAlias(), oja );
			positionsByAlias.put( oja.getRhsAlias(), position );
			EntityPersister entityPersister = null;
			if ( oja.getJoinableType().isCollectionType() ) {
				entityPersister = ( ( QueryableCollection) oja.getJoinable() ).getElementPersister();
			}
			else if ( oja.getJoinableType().isEntityType() ) {
				entityPersister = ( EntityPersister ) oja.getJoinable();
			}
			if ( entityPersister != null
					&& entityPersister.getIdentifierType().isComponentType()
					&& ! entityPersister.getEntityMetamodel().getIdentifierProperty().isEmbedded()
					&& hasAssociation( (CompositeType) entityPersister.getIdentifierType() ) ) {
				aliasesForAssociationsWithCompositesIds.add( oja.getRhsAlias() );
			}
		}
