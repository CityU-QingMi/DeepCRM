	public PluralAttributeSequentialIndexSourceImpl(
			MappingDocument sourceMappingDocument,
			final JaxbHbmListIndexType jaxbListIndex) {
		super( sourceMappingDocument );
		this.base = Integer.parseInt( jaxbListIndex.getBase() );
		this.xmlNodeName = null;
		this.typeSource = new HibernateTypeSourceImpl( "integer" );
		this.valueSources = RelationalValueSourceHelper.buildValueSources(
				sourceMappingDocument,
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					final List<JaxbHbmColumnType> columnElements = jaxbListIndex.getColumn() == null
							? Collections.<JaxbHbmColumnType>emptyList()
							: Collections.singletonList( jaxbListIndex.getColumn() );

					@Override
					public XmlElementMetadata getSourceType() {
						return XmlElementMetadata.LIST_INDEX;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getColumnAttribute() {
						return jaxbListIndex.getColumnAttribute();
					}

					@Override
					public List getColumnOrFormulaElements() {
						return columnElements;
					}

				}
		);
	}
