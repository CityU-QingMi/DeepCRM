	@SuppressWarnings("")
	protected String determineConvertedValue(AttributeConverterTypeAdapter converterTypeAdapter, Object literalValue) {
		if ( getDataType().getReturnedClass().equals( converterTypeAdapter.getModelType() ) ) {
			// apply the converter
			final AttributeConverter converter = converterTypeAdapter.getAttributeConverter();
			final Object converted = converter.convertToDatabaseColumn( getLiteralValue() );
			if ( isCharacterData( converterTypeAdapter.sqlType() ) ) {
				return "'" + converted.toString() + "'";
			}
			else {
				return converted.toString();
			}
		}
		else if ( getDataType().getReturnedClass().equals( converterTypeAdapter.getJdbcType() ) ) {
			if ( isCharacterData( converterTypeAdapter.sqlType() ) ) {
				return "'" + literalValue.toString() + "'";
			}
			else {
				return literalValue.toString();
			}
		}
		else {
			throw new QueryException(
					String.format(
							Locale.ROOT,
							"AttributeConverter domain-model attribute type [%s] and JDBC type [%s] did not match query literal type [%s]",
							converterTypeAdapter.getModelType().getName(),
							converterTypeAdapter.getJdbcType().getName(),
							getDataType().getReturnedClass().getName()
					)
			);
		}
	}
