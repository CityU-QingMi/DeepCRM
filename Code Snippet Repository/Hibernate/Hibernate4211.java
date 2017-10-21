	private void visitAttributeDefinition(AttributeDefinition attributeDefinition) {
		final PropertyPath subPath = currentPropertyPath.append( attributeDefinition.getName() );
		log.debug( "Visiting attribute path : " + subPath.getFullPath() );


		if ( attributeDefinition.getType().isAssociationType() ) {
			final AssociationAttributeDefinition associationAttributeDefinition =
					(AssociationAttributeDefinition) attributeDefinition;
			final AssociationKey associationKey = associationAttributeDefinition.getAssociationKey();
			if ( isDuplicateAssociationKey( associationKey ) ) {
				log.debug( "Property path deemed to be circular : " + subPath.getFullPath() );
				strategy.foundCircularAssociation( associationAttributeDefinition );
				// EARLY EXIT!!!
				return;
			}
		}


		boolean continueWalk = strategy.startingAttribute( attributeDefinition );
		if ( continueWalk ) {
			final PropertyPath old = currentPropertyPath;
			currentPropertyPath = subPath;
			try {
				final Type attributeType = attributeDefinition.getType();
				if ( attributeType.isAssociationType() ) {
					visitAssociation( (AssociationAttributeDefinition) attributeDefinition );
				}
				else if ( attributeType.isComponentType() ) {
					visitCompositeDefinition( (CompositionDefinition) attributeDefinition );
				}
			}
			finally {
				currentPropertyPath = old;
			}
		}
		strategy.finishingAttribute( attributeDefinition );
	}
