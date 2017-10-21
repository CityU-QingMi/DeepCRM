	private Element cloneAndSetupRevisionInfoRelationMapping() {
		final Element revMapping = (Element) revisionInfoRelationMapping.clone();
		revMapping.addAttribute( "name", verEntCfg.getRevisionFieldName() );
		if ( globalCfg.isCascadeDeleteRevision() ) {
			revMapping.addAttribute( "on-delete", "cascade" );
		}

		MetadataTools.addOrModifyColumn( revMapping, verEntCfg.getRevisionFieldName() );

		return revMapping;
	}
