	private SharedSessionContractImplementor deriveRootSession(Criteria criteria) {
		if ( criteria instanceof CriteriaImpl ) {
			return ( (CriteriaImpl) criteria ).getSession();
		}
		else if ( criteria instanceof CriteriaImpl.Subcriteria ) {
			return deriveRootSession( ( (CriteriaImpl.Subcriteria) criteria ).getParent() );
		}
		else {
			// could happen for custom Criteria impls.  Not likely, but...
			// 		for long term solution, see HHH-3514
			return null;
		}
	}
