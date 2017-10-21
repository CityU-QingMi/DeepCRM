	protected T getReplacement(T original, T target, SharedSessionContractImplementor session) {
		if ( !isMutable() ) {
			return original;
		}
		else if ( isEqual( original, target ) ) {
			return original;
		}
		else {
			return deepCopy( original );
		}
	}
