	private boolean areCompatible(Type target, Type source) {
		if ( target.equals( source ) ) {
			// if the types report logical equivalence, return true...
			return true;
		}

		// otherwise, doAfterTransactionCompletion a "deep equivalence" check...

		if ( !target.getReturnedClass().isAssignableFrom( source.getReturnedClass() ) ) {
			return false;
		}

		int[] targetDatatypes = target.sqlTypes( getSessionFactoryHelper().getFactory() );
		int[] sourceDatatypes = source.sqlTypes( getSessionFactoryHelper().getFactory() );

		if ( targetDatatypes.length != sourceDatatypes.length ) {
			return false;
		}

		for ( int i = 0; i < targetDatatypes.length; i++ ) {
			if ( !areSqlTypesCompatible( targetDatatypes[i], sourceDatatypes[i] ) ) {
				return false;
			}
		}

		return true;
	}
