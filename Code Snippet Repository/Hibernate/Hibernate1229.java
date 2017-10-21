	private static PersistentClass makePersistentClass(
			InheritanceState inheritanceState,
			PersistentClass superEntity,
			MetadataBuildingContext metadataBuildingContext) {
		//we now know what kind of persistent entity it is
		if ( !inheritanceState.hasParents() ) {
			return new RootClass( metadataBuildingContext );
		}
		else if ( InheritanceType.SINGLE_TABLE.equals( inheritanceState.getType() ) ) {
			return new SingleTableSubclass( superEntity, metadataBuildingContext );
		}
		else if ( InheritanceType.JOINED.equals( inheritanceState.getType() ) ) {
			return new JoinedSubclass( superEntity, metadataBuildingContext );
		}
		else if ( InheritanceType.TABLE_PER_CLASS.equals( inheritanceState.getType() ) ) {
			return new UnionSubclass( superEntity, metadataBuildingContext );
		}
		else {
			throw new AssertionFailure( "Unknown inheritance type: " + inheritanceState.getType() );
		}
	}
