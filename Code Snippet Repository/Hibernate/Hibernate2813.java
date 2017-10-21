	Queryable getEntityPersisterUsingImports(String className) {
		final String importedClassName = getFactory().getMetamodel().getImportedClassName( className );
		if ( importedClassName == null ) {
			return null;
		}
		try {
			return (Queryable) getFactory().getMetamodel().entityPersister( importedClassName );
		}
		catch (MappingException me) {
			return null;
		}
	}
