	private FromElement createAndAddFromElement(
			String className,
			String classAlias,
			EntityPersister entityPersister,
			EntityType type,
			String tableAlias) {
		if ( !( entityPersister instanceof Joinable ) ) {
			throw new IllegalArgumentException( "EntityPersister " + entityPersister + " does not implement Joinable!" );
		}
		FromElement element = createFromElement( entityPersister );
		initializeAndAddFromElement( element, className, classAlias, entityPersister, type, tableAlias );
		return element;
	}
