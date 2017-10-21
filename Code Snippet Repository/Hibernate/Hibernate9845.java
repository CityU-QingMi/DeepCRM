	@SuppressWarnings({""})
	private Triple<Element, ExtendedPropertyMapper, String> generateMappingData(
			PersistentClass pc, EntityXmlMappingData xmlMappingData, AuditTableData auditTableData,
			IdMappingData idMapper) {
		final Element classMapping = MetadataTools.createEntity(
				xmlMappingData.getMainXmlMapping(),
				auditTableData,
				pc.getDiscriminatorValue(),
				pc.isAbstract()
		);
		final ExtendedPropertyMapper propertyMapper = new MultiPropertyMapper();

		// Checking if there is a discriminator column
		if ( pc.getDiscriminator() != null ) {
			final Element discriminatorElement = classMapping.addElement( "discriminator" );
			// Database column or SQL formula allowed to distinguish entity types
			MetadataTools.addColumnsOrFormulas( discriminatorElement, pc.getDiscriminator().getColumnIterator() );
			discriminatorElement.addAttribute( "type", pc.getDiscriminator().getType().getName() );
		}

		// Adding the id mapping
		classMapping.add( (Element) idMapper.getXmlMapping().clone() );

		// Adding the "revision type" property
		addRevisionType( classMapping, classMapping );

		return Triple.make( classMapping, propertyMapper, null );
	}
