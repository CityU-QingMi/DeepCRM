	private void visitAssociation(AssociationAttributeDefinition attribute) {
		// todo : do "too deep" checks; but see note about adding depth to PropertyPath
		//
		// may also need to better account for "composite fetches" in terms of "depth".

		addAssociationKey( attribute.getAssociationKey() );

		final AssociationAttributeDefinition.AssociationNature nature = attribute.getAssociationNature();
		if ( nature == AssociationAttributeDefinition.AssociationNature.ANY ) {
			visitAnyDefinition( attribute.toAnyDefinition() );
		}
		else if ( nature == AssociationAttributeDefinition.AssociationNature.COLLECTION ) {
			visitCollectionDefinition( attribute.toCollectionDefinition() );
		}
		else {
			visitEntityDefinition( attribute.toEntityDefinition() );
		}
	}
