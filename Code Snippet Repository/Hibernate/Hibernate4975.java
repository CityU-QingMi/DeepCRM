	protected Joinable getJoinable() {
		if ( getAssociationNature() == AssociationNature.ANY ) {
			throw new WalkingException( "Cannot resolve AnyType to a Joinable" );
		}

		if ( joinable == null ) {
			joinable = getType().getAssociatedJoinable( sessionFactory() );
		}
		return joinable;
	}
