	public EntityPersister findEntityPersisterByName(String name) throws MappingException {
		// First, try to get the persister using the given name directly.
		try {
			return sfi.getMetamodel().entityPersister( name );
		}
		catch ( MappingException ignore ) {
			// unable to locate it using this name
		}

		// If that didn't work, try using the 'import' name.
		String importedClassName = sfi.getMetamodel().getImportedClassName( name );
		if ( importedClassName == null ) {
			return null;
		}
		return sfi.getMetamodel().entityPersister( importedClassName );
	}
