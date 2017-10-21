	public PluralAttributeMapKeyManyToManySourceImpl(
			MappingDocument mappingDocument,
			PluralAttributeSourceMapImpl pluralAttributeSourceMap,
			final JaxbHbmIndexManyToManyType jaxbIndexManyToManyMapping) {
		this.referencedEntityName = StringHelper.isNotEmpty( jaxbIndexManyToManyMapping.getEntityName() )
				? jaxbIndexManyToManyMapping.getEntityName()
				: mappingDocument.qualifyClassName( jaxbIndexManyToManyMapping.getClazz() );
		this.fkName = jaxbIndexManyToManyMapping.getForeignKey();

		this.hibernateTypeSource = new HibernateTypeSourceImpl(
				jaxbIndexManyToManyMapping.getEntityName()
		);

		this.relationalValueSources = RelationalValueSourceHelper.buildValueSources(
				mappingDocument,
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.INDEX_MANY_TO_MANY;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getColumnAttribute() {
						return jaxbIndexManyToManyMapping.getColumnAttribute();
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbIndexManyToManyMapping.getColumn();
					}
				}
		);
	}
