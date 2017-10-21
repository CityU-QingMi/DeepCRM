	public PluralAttributeMapKeySourceBasicImpl(
			MappingDocument sourceMappingDocument,
			final JaxbHbmMapKeyBasicType jaxbMapKey) {
		super( sourceMappingDocument );
		this.typeSource = new HibernateTypeSourceImpl( jaxbMapKey );
		this.valueSources = RelationalValueSourceHelper.buildValueSources(
				sourceMappingDocument(),
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.MAP_KEY;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getFormulaAttribute() {
						return jaxbMapKey.getFormulaAttribute();
					}

					@Override
					public String getColumnAttribute() {
						return jaxbMapKey.getColumnAttribute();
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbMapKey.getColumnOrFormula();
					}

					@Override
					public SizeSource getSizeSource() {
						return Helper.interpretSizeSource(
								jaxbMapKey.getLength(),
								(Integer) null,
								null
						);
					}
				}
		);
		this.xmlNodeName = jaxbMapKey.getNode();
	}
