		public int[][] resolve() {
			int[][] compositeKeyManyToOneTargetIndices = null;
			for ( final String aliasWithCompositeId : aliasesForAssociationsWithCompositesIds ) {
				final OuterJoinableAssociation joinWithCompositeId = associationsByAlias.get( aliasWithCompositeId );
				final ArrayList<Integer> keyManyToOneTargetIndices = new ArrayList<Integer>();
				// for each association with a composite id containing key-many-to-one(s), find the bidirectional side of
				// each key-many-to-one (if exists) to see if it is eager as well.  If so, we need to track the indices
				EntityPersister entityPersister = null;
				if ( joinWithCompositeId.getJoinableType().isCollectionType() ) {
					entityPersister = ( ( QueryableCollection) joinWithCompositeId.getJoinable() ).getElementPersister();
				}
				else if ( joinWithCompositeId.getJoinableType().isEntityType() ) {
					entityPersister = ( EntityPersister ) joinWithCompositeId.getJoinable();
				}

				findKeyManyToOneTargetIndices(
						keyManyToOneTargetIndices,
						joinWithCompositeId,
						(CompositeType) entityPersister.getIdentifierType()
				);

				if ( ! keyManyToOneTargetIndices.isEmpty() ) {
					if ( compositeKeyManyToOneTargetIndices == null ) {
						compositeKeyManyToOneTargetIndices = new int[ associationsByAlias.size() ][];
					}
					int position = positionsByAlias.get( aliasWithCompositeId );
					compositeKeyManyToOneTargetIndices[position] = new int[ keyManyToOneTargetIndices.size() ];
					int i = 0;
					for ( int index : keyManyToOneTargetIndices ) {
						compositeKeyManyToOneTargetIndices[position][i] = index;
						i++;
					}
				}
			}
			return compositeKeyManyToOneTargetIndices;
		}
