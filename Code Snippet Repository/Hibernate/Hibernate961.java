	public static List<ColumnSource> buildColumnSources(
			MappingDocument mappingDocument,
			String containingTableName,
			RelationalValueSourceHelper.ColumnsAndFormulasSource columnsAndFormulasSource) {
		final List<RelationalValueSource> sources = buildValueSources(
				mappingDocument,
				containingTableName,
				columnsAndFormulasSource
		);

		final List<ColumnSource> columnSources = CollectionHelper.arrayList( sources.size() );
		for ( RelationalValueSource source : sources ) {
			if ( !ColumnSource.class.isInstance( source ) ) {
				final String errorMessage;
				if ( columnsAndFormulasSource.getSourceType().canBeNamed()
						&& StringHelper.isNotEmpty( columnsAndFormulasSource.getSourceName() ) ) {
					errorMessage = String.format(
							Locale.ENGLISH,
							"Expecting only columns in context of <%s name=\"%s\"/>, but found formula [%s]",
							columnsAndFormulasSource.getSourceType().getElementName(),
							columnsAndFormulasSource.getSourceName(),
							( (DerivedValueSource) source ).getExpression()
					);
				}
				else {
					errorMessage = String.format(
							Locale.ENGLISH,
							"Expecting only columns in context of <%s/>, but found formula [%s]",
							columnsAndFormulasSource.getSourceType().getElementName(),
							( (DerivedValueSource) source ).getExpression()
					);
				}
				throw new MappingException( errorMessage, mappingDocument.getOrigin() );
			}
			columnSources.add( (ColumnSource) source );
		}
		return columnSources;
	}
