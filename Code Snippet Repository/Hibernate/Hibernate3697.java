	@Override
	public boolean startingAttribute(AttributeDefinition attributeDefinition) {
		log.tracef(
				"%s Starting attribute %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				attributeDefinition
		);

		final Type attributeType = attributeDefinition.getType();

		final boolean isComponentType = attributeType.isComponentType();
		final boolean isAssociationType = attributeType.isAssociationType();
		final boolean isBasicType = ! ( isComponentType || isAssociationType );
		currentPropertyPath = currentPropertyPath.append( attributeDefinition.getName() );
		if ( isBasicType ) {
			return true;
		}
		else if ( isAssociationType ) {
			// also handles any type attributes...
			return handleAssociationAttribute( (AssociationAttributeDefinition) attributeDefinition );
		}
		else {
			return handleCompositeAttribute( attributeDefinition );
		}
	}
