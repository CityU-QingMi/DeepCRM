	@Override
	public AssociationNature getAssociationNature() {
		if ( getType().isAnyType() ) {
			return AssociationNature.ANY;
		}
		else {
			if ( getJoinable().isCollection() ) {
				return AssociationNature.COLLECTION;
			}
			else {
				return AssociationNature.ENTITY;
			}
		}
	}
