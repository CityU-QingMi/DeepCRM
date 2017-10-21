	private static void validateCustomWriteFragment(
			MappingDocument sourceDocument,
			RelationalValueSourceHelper.ColumnsAndFormulasSource columnsAndFormulasSource,
			JaxbHbmColumnType columnMapping,
			String customWrite) {
		if ( customWrite != null && !customWrite.matches("[^?]*\\?[^?]*") ) {
			final String errorMessage;
			if ( columnsAndFormulasSource.getSourceType().canBeNamed()
					&& StringHelper.isNotEmpty( columnsAndFormulasSource.getSourceName() ) ) {
				errorMessage = String.format(
						Locale.ENGLISH,
						"write expression must contain exactly one value placeholder ('?') character near <column name=\"%s\" ... write=\"%s\" /> for <%s name=\"%s\" />",
						columnMapping.getName(),
						customWrite,
						columnsAndFormulasSource.getSourceType().getElementName(),
						columnsAndFormulasSource.getSourceName()
				);
			}
			else {
				errorMessage = String.format(
						Locale.ENGLISH,
						"write expression must contain exactly one value placeholder ('?') character near <column name=\"%s\" ... write=\"%s\" /> for <%s />",
						columnMapping.getName(),
						customWrite,
						columnsAndFormulasSource.getSourceType().getElementName()
				);
			}
			throw new MappingException( errorMessage, sourceDocument.getOrigin() );
		}
	}
