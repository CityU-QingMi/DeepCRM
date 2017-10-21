	@Override
	public void processQueryRenames() {
		for ( JaxbHbmClassRenameType renameBinding : documentRoot.getImport() ) {
			final String name = qualifyClassName( renameBinding.getClazz() );
			final String rename = renameBinding.getRename() == null
					? StringHelper.unqualify( name )
					: renameBinding.getRename();
			getMetadataCollector().addImport( rename, name );
			log.debugf( "Import (query rename): %s -> %s", rename, name );
		}
	}
