	public void generateSecondPass(String entityName, PersistentClass persistentClass) {
		final Component identifierMapper = persistentClass.getIdentifierMapper();
		final Property identifierProperty = persistentClass.getIdentifierProperty();
		if ( identifierMapper != null ) {
			generateSecondPass( entityName, identifierMapper );
		}
		else if ( identifierProperty != null && identifierProperty.isComposite() ) {
			final Component component = (Component) identifierProperty.getValue();
			generateSecondPass( entityName, component );
		}
	}
