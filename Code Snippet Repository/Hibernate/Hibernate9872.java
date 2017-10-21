	private void storeMiddleEntityRelationInformation(String mappedBy) {
		// Only if this is a relation (when there is a referenced entity).
		if ( referencedEntityName != null ) {
			if ( propertyValue.isInverse() ) {
				referencingEntityConfiguration.addToManyMiddleNotOwningRelation(
						propertyName,
						mappedBy,
						referencedEntityName
				);
			}
			else {
				referencingEntityConfiguration.addToManyMiddleRelation( propertyName, referencedEntityName );
			}
		}
	}
