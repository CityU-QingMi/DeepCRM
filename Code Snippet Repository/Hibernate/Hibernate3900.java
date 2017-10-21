	public Component getIdentifierMapper() {
		//get direct identifiermapper or the one from the super mappedSuperclass
		// or the one from the super persistentClass
		Component propagatedMapper = identifierMapper;
		if ( propagatedMapper == null ) {
			if ( superMappedSuperclass != null ) {
				propagatedMapper = superMappedSuperclass.getIdentifierMapper();
			}
			if (propagatedMapper == null && superPersistentClass != null){
				propagatedMapper = superPersistentClass.getIdentifierMapper();
			}
		}
		return propagatedMapper;
	}
