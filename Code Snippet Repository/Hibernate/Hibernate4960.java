	private static MappedIdentifierValueMarshaller buildMappedIdentifierValueMarshaller(
			SessionFactoryImplementor sessionFactory,
			ComponentType mappedIdClassComponentType,
			ComponentType virtualIdComponent) {
		// so basically at this point we know we have a "mapped" composite identifier
		// which is an awful way to say that the identifier is represented differently
		// in the entity and in the identifier value.  The incoming value should
		// be an instance of the mapped identifier class (@IdClass) while the incoming entity
		// should be an instance of the entity class as defined by metamodel.
		//
		// However, even within that we have 2 potential scenarios:
		//		1) @IdClass types and entity @Id property types match
		//			- return a NormalMappedIdentifierValueMarshaller
		//		2) They do not match
		//			- return a IncrediblySillyJpaMapsIdMappedIdentifierValueMarshaller
		boolean wereAllEquivalent = true;
		// the sizes being off is a much bigger problem that should have been caught already...
		for ( int i = 0; i < virtualIdComponent.getSubtypes().length; i++ ) {
			if ( virtualIdComponent.getSubtypes()[i].isEntityType()
					&& !mappedIdClassComponentType.getSubtypes()[i].isEntityType() ) {
				wereAllEquivalent = false;
				break;
			}
		}

		return wereAllEquivalent
				? new NormalMappedIdentifierValueMarshaller( virtualIdComponent, mappedIdClassComponentType )
				: new IncrediblySillyJpaMapsIdMappedIdentifierValueMarshaller(
						sessionFactory,
						virtualIdComponent,
						mappedIdClassComponentType
		);
	}
