	private Element generateRevisionInfoRelationMapping() {
		final Document document = globalCfg.getEnversService().getXmlHelper().getDocumentFactory().createDocument();
		final Element revRelMapping = document.addElement( "key-many-to-one" );
		revRelMapping.addAttribute( "type", revisionPropType );
		revRelMapping.addAttribute( "class", revisionInfoEntityName );

		if ( revisionPropSqlType != null ) {
			// Putting a fake name to make Hibernate happy. It will be replaced later anyway.
			MetadataTools.addColumn( revRelMapping, "*", null, null, null, revisionPropSqlType, null, null, false );
		}

		return revRelMapping;
	}
