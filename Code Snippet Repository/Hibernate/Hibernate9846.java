	private Triple<Element, ExtendedPropertyMapper, String> generateInheritanceMappingData(
			PersistentClass pc, EntityXmlMappingData xmlMappingData, AuditTableData auditTableData,
			String inheritanceMappingType) {
		final String extendsEntityName = verEntCfg.getAuditEntityName( pc.getSuperclass().getEntityName() );
		final Element classMapping = MetadataTools.createSubclassEntity(
				xmlMappingData.getMainXmlMapping(),
				inheritanceMappingType,
				auditTableData,
				extendsEntityName,
				pc.getDiscriminatorValue(),
				pc.isAbstract()
		);

		// The id and revision type is already mapped in the parent

		// Getting the property mapper of the parent - when mapping properties, they need to be included
		final String parentEntityName = pc.getSuperclass().getEntityName();

		final EntityConfiguration parentConfiguration = entitiesConfigurations.get( parentEntityName );
		if ( parentConfiguration == null ) {
			throw new MappingException(
					"Entity '" + pc.getEntityName() + "' is audited, but its superclass: '" +
							parentEntityName + "' is not."
			);
		}

		final ExtendedPropertyMapper parentPropertyMapper = parentConfiguration.getPropertyMapper();
		final ExtendedPropertyMapper propertyMapper = new SubclassPropertyMapper(
				new MultiPropertyMapper(),
				parentPropertyMapper
		);

		return Triple.make( classMapping, propertyMapper, parentEntityName );
	}
