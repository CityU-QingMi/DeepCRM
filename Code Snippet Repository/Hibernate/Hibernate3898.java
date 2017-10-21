	public Property getIdentifierProperty() {
		//get direct identifiermapper or the one from the super mappedSuperclass
		// or the one from the super persistentClass
		Property propagatedIdentifierProp = identifierProperty;
		if ( propagatedIdentifierProp == null ) {
			if ( superMappedSuperclass != null ) {
				propagatedIdentifierProp = superMappedSuperclass.getIdentifierProperty();
			}
			if (propagatedIdentifierProp == null && superPersistentClass != null){
				propagatedIdentifierProp = superPersistentClass.getIdentifierProperty();
			}
		}
		return propagatedIdentifierProp;
	}
