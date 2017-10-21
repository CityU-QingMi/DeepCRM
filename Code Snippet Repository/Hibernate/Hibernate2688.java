	public JoinSequence getJoinSequence() {
		if ( joinSequence != null ) {
			return joinSequence;
		}

		// Class names in the FROM clause result in a JoinSequence (the old FromParser does this).
		if ( persister instanceof Joinable ) {
			Joinable joinable = (Joinable) persister;
			final JoinSequence joinSequence = fromElement.getSessionFactoryHelper().createJoinSequence().setRoot(
					joinable,
					getTableAlias()
			);
			joinSequence.applyTreatAsDeclarations( treatAsDeclarations );
			return joinSequence;
		}
		else {
			// TODO: Should this really return null?  If not, figure out something better to do here.
			return null;
		}
	}
