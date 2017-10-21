	private FromImplementor locateImplicitSelection() {
		FromImplementor implicitSelection = null;

		if ( ! isSubQuery ) {
			// we should have only a single root (query validation should have checked this...)
			implicitSelection = (FromImplementor) getRoots().iterator().next();
		}
		else {
			// we should only have a single "root" which can act as the implicit selection
			final Set<Join<?, ?>> correlatedJoins = collectCorrelatedJoins();
			if ( correlatedJoins != null ) {
				if ( correlatedJoins.size() == 1 ) {
					implicitSelection = (FromImplementor) correlatedJoins.iterator().next();
				}
			}
		}

		if ( implicitSelection == null ) {
			throw new IllegalStateException( "No explicit selection and an implicit one could not be determined" );
		}

		return implicitSelection;
	}
