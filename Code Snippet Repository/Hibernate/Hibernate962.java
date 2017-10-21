	private static void validateUseOfColumnOrFormulaNestedElements(
			MappingDocument sourceDocument,
			RelationalValueSourceHelper.ColumnsAndFormulasSource columnsAndFormulasSource) {
		if ( StringHelper.isNotEmpty( columnsAndFormulasSource.getColumnAttribute() ) ) {
			final String errorMessage;
			if ( columnsAndFormulasSource.getSourceType().canBeNamed()
					&& StringHelper.isNotEmpty( columnsAndFormulasSource.getSourceName() ) ) {
				errorMessage = String.format(
						Locale.ENGLISH,
						"column attribute may not be specified along with <column/> or <formula/> subelement(s) near <%s name=\"%s\" column=\"%s\" />",
						columnsAndFormulasSource.getSourceType().getElementName(),
						columnsAndFormulasSource.getSourceName(),
						columnsAndFormulasSource.getColumnAttribute()
				);
			}
			else {
				errorMessage = String.format(
						Locale.ENGLISH,
						"column attribute may not be specified along with <column/> or <formula/> subelement(s) near <%s column=\"%s\" />",
						columnsAndFormulasSource.getSourceType().getElementName(),
						columnsAndFormulasSource.getColumnAttribute()
				);
			}
			throw new MappingException( errorMessage, sourceDocument.getOrigin() );
		}
	}
