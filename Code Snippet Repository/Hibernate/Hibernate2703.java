	private boolean isSuperclassProperty(String propertyName) {
		// really there are two situations where it should be ok to allow the insertion
		// into properties defined on a superclass:
		//      1) union-subclass with an abstract root entity
		//      2) discrim-subclass
		//
		// #1 is handled already because of the fact that
		// UnionSubclassPersister alreay always returns 0
		// for this call...
		//
		// we may want to disallow it for discrim-subclass just for
		// consistency-sake (currently does not work anyway)...
		return persister.getSubclassPropertyTableNumber( propertyName ) != 0;
	}
