	void addRevisionType(Element anyMapping, Element anyMappingEnd, boolean isKey) {
		final Element revTypeProperty = MetadataTools.addProperty(
				anyMapping,
				verEntCfg.getRevisionTypePropName(),
				verEntCfg.getRevisionTypePropType(),
				true,
				isKey
		);
		revTypeProperty.addAttribute( "type", "org.hibernate.envers.internal.entities.RevisionTypeType" );

		// Adding the end revision, if appropriate
		addEndRevision( anyMappingEnd );
	}
