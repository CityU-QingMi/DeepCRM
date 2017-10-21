	@Override
	public AssociationNature getAssociationNature() {
		if ( getType().isAnyType() ) {
			return AssociationNature.ANY;
		}
		else {
			if ( getType().isCollectionType() ) {
				return AssociationNature.COLLECTION;
			}
			else {
				return AssociationNature.ENTITY;
			}
		}
	}
