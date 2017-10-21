	public static Queryable findQueryableUsingImports(SessionFactoryImplementor sfi, String className) {
		final String importedClassName = sfi.getMetamodel().getImportedClassName( className );
		if ( importedClassName == null ) {
			return null;
		}
		try {
			return (Queryable) sfi.getMetamodel().entityPersister( importedClassName );
		}
		catch ( MappingException me ) {
			return null;
		}
	}
