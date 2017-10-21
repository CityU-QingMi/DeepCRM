	@Override
	public void finishingEntityIdentifier(EntityIdentifierDefinition identifierDefinition) {
		super.finishingEntityIdentifier( identifierDefinition );
		if ( vetoHandleAssociationAttribute !=
				shouldVetoHandleAssociationAttributeInId( rootReturn, identifierDefinition ) ) {
			throw new WalkingException(
					"vetoHandleAssociationAttribute has unexpected value: " + vetoHandleAssociationAttribute
			);
		}
		vetoHandleAssociationAttribute = false;
	}
