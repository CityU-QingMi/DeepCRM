	public PluralAttributeSequentialIndexSourceImpl(
			MappingDocument sourceMappingDocument,
			final JaxbHbmIndexType jaxbIndex) {
		super( sourceMappingDocument );
		this.base = 0;
		this.xmlNodeName = null;
		if ( StringHelper.isEmpty( jaxbIndex.getType() ) ) {
			this.typeSource = new HibernateTypeSourceImpl( "integer" );
		}
		else {
			this.typeSource = new HibernateTypeSourceImpl( jaxbIndex.getType() );
		}
		this.valueSources = RelationalValueSourceHelper.buildValueSources(
				sourceMappingDocument,
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.INDEX;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getColumnAttribute() {
						return jaxbIndex.getColumnAttribute();
					}

					@Override
					public SizeSource getSizeSource() {
						return Helper.interpretSizeSource(
								jaxbIndex.getLength(),
								(Integer) null,
								null
						);
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbIndex.getColumn();
					}
				}
		);
	}
