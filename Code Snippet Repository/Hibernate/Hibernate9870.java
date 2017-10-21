	private String getMiddleTableName(Collection value, String entityName) {
		// We check how Hibernate maps the collection.
		if ( value.getElement() instanceof OneToMany && !value.isInverse() ) {
			// This must be a @JoinColumn+@OneToMany mapping. Generating the table name, as Hibernate doesn't use a
			// middle table for mapping this relation.
			return StringTools.getLastComponent( entityName ) + "_" + StringTools.getLastComponent(
					MappingTools.getReferencedEntityName(
							value.getElement()
					)
			);
		}
		// Hibernate uses a middle table for mapping this relation, so we get it's name directly.
		return value.getCollectionTable().getName();
	}
