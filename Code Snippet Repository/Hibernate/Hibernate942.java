	public PluralAttributeKeySourceImpl(
			MappingDocument mappingDocument,
			final JaxbHbmKeyType jaxbKey,
			final AttributeSourceContainer container) {
		super( mappingDocument );

		this.explicitFkName = StringHelper.nullIfEmpty( jaxbKey.getForeignKey() );
		this.referencedPropertyName = StringHelper.nullIfEmpty( jaxbKey.getPropertyRef() );
		this.cascadeDeletesAtFkLevel = jaxbKey.getOnDelete() != null
				&& "cascade".equals( jaxbKey.getOnDelete().value() );
		this.nullable = jaxbKey.isNotNull() == null || !jaxbKey.isNotNull();
		this.updateable = jaxbKey.isUpdate() == null || jaxbKey.isUpdate();

		this.valueSources = RelationalValueSourceHelper.buildValueSources(
				sourceMappingDocument(),
				null, // todo : collection table name
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.KEY;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getColumnAttribute() {
						return StringHelper.nullIfEmpty( jaxbKey.getColumnAttribute() );
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbKey.getColumn();
					}

				}
		);
	}
