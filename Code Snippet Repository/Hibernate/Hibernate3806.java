	private void renderJoin(Join join, JoinFragment joinFragment) {
		if ( CompositeQuerySpace.class.isInstance( join.getRightHandSide() ) ) {
			handleCompositeJoin( join, joinFragment );
		}
		else if ( EntityQuerySpace.class.isInstance( join.getRightHandSide() ) ) {
			// do not render the entity join for a one-to-many association, since the collection join
			// already joins to the associated entity table (see doc in renderCollectionJoin()).
			if ( join.getLeftHandSide().getDisposition() == QuerySpace.Disposition.COLLECTION ) {
				if ( CollectionQuerySpace.class.cast( join.getLeftHandSide() ).getCollectionPersister().isManyToMany() ) {
					renderManyToManyJoin( join, joinFragment );
				}
				else if ( JoinDefinedByMetadata.class.isInstance( join ) &&
						CollectionPropertyNames.COLLECTION_INDICES.equals( JoinDefinedByMetadata.class.cast( join ).getJoinedPropertyName() ) ) {
					renderManyToManyJoin( join, joinFragment );
				}
			}
			else {
				renderEntityJoin( join, joinFragment );
			}
		}
		else if ( CollectionQuerySpace.class.isInstance( join.getRightHandSide() ) ) {
			renderCollectionJoin( join, joinFragment );
		}
	}
