	@Override
	protected boolean isSubclassTableIndicatedByTreatAsDeclarations(
			int subclassTableNumber,
			Set<String> treatAsDeclarations) {
		if ( treatAsDeclarations == null || treatAsDeclarations.isEmpty() ) {
			return false;
		}

		final String[] inclusionSubclassNameClosure = getSubclassNameClosureBySubclassTable( subclassTableNumber );

		// NOTE : we assume the entire hierarchy is joined-subclass here
		for ( String subclassName : treatAsDeclarations ) {
			for ( String inclusionSubclassName : inclusionSubclassNameClosure ) {
				if ( inclusionSubclassName.equals( subclassName ) ) {
					return true;
				}
			}
		}

		return false;
	}
