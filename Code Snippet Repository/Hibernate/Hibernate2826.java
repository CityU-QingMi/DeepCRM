	protected List<Object[]> selectIds(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters) {
		List<Object[]> ids = new ArrayList<>();
		try {
			try (PreparedStatement ps = session.getJdbcCoordinator()
					.getStatementPreparer()
					.prepareStatement( idSelect, false )) {
				int position = 1;
				for ( ParameterSpecification parameterSpecification : idSelectParameterSpecifications ) {
					position += parameterSpecification.bind( ps, queryParameters, session, position );
				}

				ResultSet rs = session
						.getJdbcCoordinator()
						.getResultSetReturn()
						.extract( ps );
				while ( rs.next() ) {
					Object[] result = new Object[targetedPersister.getIdentifierColumnNames().length];
					for ( String columnName : targetedPersister.getIdentifierColumnNames() ) {
						Object column = rs.getObject( columnName );
						result[rs.findColumn( columnName ) - 1] = column;
					}
					ids.add( result );
				}
			}
		}
		catch ( SQLException e ) {
			throw convert( e, "could not select ids for bulk operation", idSelect );
		}

		return ids;
	}
