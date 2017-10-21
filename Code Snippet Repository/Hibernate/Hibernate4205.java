	private static boolean isSubsequentSelectDelayed(AssociationType type, SessionFactoryImplementor sessionFactory) {
		if ( type.isAnyType() ) {
			// we'd need more context here.  this is only kept as part of the property state on the owning entity
			return false;
		}
		else if ( type.isEntityType() ) {
			return ( (EntityPersister) type.getAssociatedJoinable( sessionFactory ) ).hasProxy();
		}
		else {
			final CollectionPersister cp = ( (CollectionPersister) type.getAssociatedJoinable( sessionFactory ) );
			return cp.isLazy() || cp.isExtraLazy();
		}
	}
