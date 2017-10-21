	public JoinedSubclassEntitySourceImpl(
			MappingDocument sourceMappingDocument,
			JaxbHbmJoinedSubclassEntityType jaxbJoinedSubclassMapping,
			EntitySource container) {
		super( sourceMappingDocument, jaxbJoinedSubclassMapping, container );
		this.jaxbKeyMapping = jaxbJoinedSubclassMapping.getKey();
		List<RelationalValueSource> valueSources = RelationalValueSourceHelper.buildValueSources(
				sourceMappingDocument(),
				null,
				new RelationalValueSourceHelper.AbstractColumnsAndFormulasSource() {
					@Override
					public XmlElementMetadata getSourceType() {
						return null;
					}

					@Override
					public String getSourceName() {
						return null;
					}

					@Override
					public String getColumnAttribute() {
						return jaxbKeyMapping.getColumnAttribute();
					}

					@Override
					public List getColumnOrFormulaElements() {
						return jaxbKeyMapping.getColumn();
					}

					@Override
					public Boolean isNullable() {
						return false;
					}
				}
		);

		this.primaryKeyJoinColumnSources = new ArrayList<ColumnSource>( valueSources.size() );
		for ( RelationalValueSource valueSource : valueSources ) {
			primaryKeyJoinColumnSources.add( ColumnSource.class.cast( valueSource ) ) ;
		}
	}
