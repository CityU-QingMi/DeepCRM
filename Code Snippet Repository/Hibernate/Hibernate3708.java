	@Override
	public void startingEntityIdentifier(EntityIdentifierDefinition identifierDefinition ) {
		if ( vetoHandleAssociationAttribute ) {
			throw new WalkingException( "vetoHandleAssociationAttribute is true when starting startingEntityIdentifier()" );
		}
		vetoHandleAssociationAttribute = shouldVetoHandleAssociationAttributeInId(
				rootReturn,
				identifierDefinition
		);
		super.startingEntityIdentifier( identifierDefinition );
	}
