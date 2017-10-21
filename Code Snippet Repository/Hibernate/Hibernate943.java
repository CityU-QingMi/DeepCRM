	public PluralAttributeMapKeyManyToManySourceImpl(
			MappingDocument mappingDocument,
			PluralAttributeSourceMapImpl pluralAttributeSourceMap,
			final JaxbHbmMapKeyManyToManyType jaxbMapKeyManyToManyMapping) {
		this.referencedEntityName = StringHelper.isNotEmpty( jaxbMapKeyManyToManyMapping.getEntityName() )
				? jaxbMapKeyManyToManyMapping.getEntityName()
				: mappingDocument.qualifyClassName( jaxbMapKeyManyToManyMapping.getClazz() );
		this.fkName = jaxbMapKeyManyToManyMapping.getForeignKey();

		this.hibernateTypeSource = new HibernateTypeSourceImpl(
				jaxbMapKeyManyToManyMapping.getEntityName()
		);

		this.relationalValueSources = RelationalValueSourceHelper.buildValueSources(
				mappingDocument,
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.MAP_KEY_MANY_TO_MANY;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getFormulaAttribute() {
						return jaxbMapKeyManyToManyMapping.getFormulaAttribute();
					}

					@Override
					public String getColumnAttribute() {
						return jaxbMapKeyManyToManyMapping.getColumnAttribute();
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbMapKeyManyToManyMapping.getColumnOrFormula();
					}
				}
		);
	}
