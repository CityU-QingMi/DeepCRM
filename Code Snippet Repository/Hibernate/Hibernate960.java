	public static RelationalValueSource buildValueSource(
			MappingDocument mappingDocument,
			String containingTableName,
			RelationalValueSourceHelper.ColumnsAndFormulasSource columnsAndFormulasSource) {
		final List<RelationalValueSource> sources = buildValueSources(
				mappingDocument,
				containingTableName,
				columnsAndFormulasSource
		);

		if ( sources.size() > 1 ) {
			final String errorMessage;
			if ( columnsAndFormulasSource.getSourceType().canBeNamed()
					&& StringHelper.isNotEmpty( columnsAndFormulasSource.getSourceName() ) ) {
				errorMessage = String.format(
						Locale.ENGLISH,
						"Expecting just a single formula/column in context of <%s name=\"%s\"/>",
						columnsAndFormulasSource.getSourceType().getElementName(),
						columnsAndFormulasSource.getSourceName()
				);
			}
			else {
				errorMessage = String.format(
						Locale.ENGLISH,
						"Expecting just a single formula/column in context of <%s/>",
						columnsAndFormulasSource.getSourceType().getElementName()
				);
			}
			throw new MappingException( errorMessage, mappingDocument.getOrigin() );
		}

		return sources.get( 0 );
	}
