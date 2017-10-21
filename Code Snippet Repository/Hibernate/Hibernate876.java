	public static InheritanceType interpretInheritanceType(JaxbHbmEntityBaseDefinition entityElement) {
		if ( JaxbHbmDiscriminatorSubclassEntityType.class.isInstance( entityElement ) ) {
			return InheritanceType.DISCRIMINATED;
		}
		else if ( JaxbHbmJoinedSubclassEntityType.class.isInstance( entityElement ) ) {
			return InheritanceType.JOINED;
		}
		else if ( JaxbHbmUnionSubclassEntityType.class.isInstance( entityElement ) ) {
			return InheritanceType.UNION;
		}
		else {
			return InheritanceType.NO_INHERITANCE;
		}
	}
