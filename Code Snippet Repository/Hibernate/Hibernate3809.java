	private AssociationType getJoinedAssociationTypeOrNull(Join join) {

		if ( !JoinDefinedByMetadata.class.isInstance( join ) ) {
			return null;
		}
		final Type joinedType = ( (JoinDefinedByMetadata) join ).getJoinedPropertyType();
		return joinedType.isAssociationType()
				? (AssociationType) joinedType
				: null;
	}
