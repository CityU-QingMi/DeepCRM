		private void findKeyManyToOneTargetIndices(
				ArrayList<Integer> keyManyToOneTargetIndices,
				OuterJoinableAssociation joinWithCompositeId,
				CompositeType componentType) {
			for ( Type subType : componentType.getSubtypes() ) {
				if ( subType.isEntityType() ) {
					Integer index = locateKeyManyToOneTargetIndex( joinWithCompositeId, (EntityType) subType );
					if ( index != null ) {
						keyManyToOneTargetIndices.add( index );
					}
				}
				else if ( subType.isComponentType() ) {
					findKeyManyToOneTargetIndices(
							keyManyToOneTargetIndices,
							joinWithCompositeId,
							(CompositeType) subType
					);
				}
			}
		}
