		private Integer locateKeyManyToOneTargetIndex(OuterJoinableAssociation joinWithCompositeId, EntityType keyManyToOneType) {
			// the lhs (if one) is a likely candidate
			if ( joinWithCompositeId.getLhsAlias() != null ) {
				final OuterJoinableAssociation lhs = associationsByAlias.get( joinWithCompositeId.getLhsAlias() );
				if ( keyManyToOneType.getAssociatedEntityName( factory ).equals( lhs.getJoinableType().getAssociatedEntityName( factory ) ) ) {
					return positionsByAlias.get( lhs.getRhsAlias() );
				}
			}
			// otherwise, seek out OuterJoinableAssociation which are RHS of given OuterJoinableAssociation
			// (joinWithCompositeId)
			for ( OuterJoinableAssociation oja : associationsByAlias.values() ) {
				if ( oja.getLhsAlias() != null && oja.getLhsAlias().equals( joinWithCompositeId.getRhsAlias() ) ) {
					if ( keyManyToOneType.equals( oja.getJoinableType() ) ) {
						return positionsByAlias.get( oja.getLhsAlias() );
					}
				}
			}
			return null;
		}
