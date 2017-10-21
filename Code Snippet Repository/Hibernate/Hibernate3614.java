	public Type getHibernateType(int columnPos) throws SQLException {
		int columnType = resultSetMetaData.getColumnType( columnPos );
		int scale = resultSetMetaData.getScale( columnPos );
		int precision = resultSetMetaData.getPrecision( columnPos );

		int length = precision;
		if ( columnType == Types.CHAR && precision == 0 ) {
			length = resultSetMetaData.getColumnDisplaySize( columnPos );
		}

		return factory.getTypeResolver().heuristicType(
				factory.getDialect().getHibernateTypeName(
						columnType,
						length,
						precision,
						scale
				)
		);
	}
