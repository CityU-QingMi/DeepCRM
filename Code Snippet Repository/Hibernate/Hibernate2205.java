	public static LinkedHashSet<TypeInfo> extractTypeInfo(DatabaseMetaData metaData) {
		final LinkedHashSet<TypeInfo> typeInfoSet = new LinkedHashSet<TypeInfo>();
		try {
			final ResultSet resultSet = metaData.getTypeInfo();
			try {
				while ( resultSet.next() ) {
					typeInfoSet.add(
							new TypeInfo(
									resultSet.getString( "TYPE_NAME" ),
									resultSet.getInt( "DATA_TYPE" ),
									interpretCreateParams( resultSet.getString( "CREATE_PARAMS" ) ),
									resultSet.getBoolean( "UNSIGNED_ATTRIBUTE" ),
									resultSet.getInt( "PRECISION" ),
									resultSet.getShort( "MINIMUM_SCALE" ),
									resultSet.getShort( "MAXIMUM_SCALE" ),
									resultSet.getBoolean( "FIXED_PREC_SCALE" ),
									resultSet.getString( "LITERAL_PREFIX" ),
									resultSet.getString( "LITERAL_SUFFIX" ),
									resultSet.getBoolean( "CASE_SENSITIVE" ),
									TypeSearchability.interpret( resultSet.getShort( "SEARCHABLE" ) ),
									TypeNullability.interpret( resultSet.getShort( "NULLABLE" ) )
							)
					);
				}
			}
			catch ( SQLException e ) {
				LOG.unableToAccessTypeInfoResultSet( e.toString() );
			}
			finally {
				try {
					resultSet.close();
				}
				catch ( SQLException e ) {
					LOG.unableToReleaseTypeInfoResultSet();
				}
			}
		}
		catch ( SQLException e ) {
			LOG.unableToRetrieveTypeInfoResultSet( e.toString() );
		}

		return typeInfoSet;
	}
