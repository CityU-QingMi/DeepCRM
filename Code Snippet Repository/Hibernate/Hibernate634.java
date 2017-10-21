	@Override
	public boolean shouldAutoApplyToCollectionElement(XProperty xProperty, MetadataBuildingContext context) {
		if ( !autoApply ) {
			return false;
		}

		final ResolvedMember collectionMember = resolveMember( xProperty, context );
		final ResolvedType elementType;

		if ( Map.class.isAssignableFrom( collectionMember.getType().getErasedType() ) ) {
			elementType = collectionMember.getType().typeParametersFor( Map.class ).get( 1 );
		}
		else if ( Collection.class.isAssignableFrom( collectionMember.getType().getErasedType() ) ) {
			elementType = collectionMember.getType().typeParametersFor( Collection.class ).get( 0 );
		}
		else {
			throw new HibernateException( "Attribute was neither a Collection nor a Map : " + collectionMember.getType().getErasedType() );
		}

		return typesMatch( domainType, elementType );
	}
