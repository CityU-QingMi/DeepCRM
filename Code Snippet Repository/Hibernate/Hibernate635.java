	@Override
	public boolean shouldAutoApplyToMapKey(XProperty xProperty, MetadataBuildingContext context) {
		if ( !autoApply ) {
			return false;
		}

		final ResolvedMember collectionMember = resolveMember( xProperty, context );
		final ResolvedType keyType;

		if ( Map.class.isAssignableFrom( collectionMember.getType().getErasedType() ) ) {
			keyType = collectionMember.getType().typeParametersFor( Map.class ).get( 0 );
		}
		else {
			throw new HibernateException( "Attribute was not a Map : " + collectionMember.getType().getErasedType() );
		}

		return typesMatch( domainType, keyType );
	}
