	@Override
	public int execute(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters) {

		CteValuesListBuilder values = prepareCteStatement( session, queryParameters );

		if ( !values.getIds().isEmpty() ) {

			// Start performing the updates
			for ( int i = 0; i < updates.length; i++ ) {
				String updateSuffix = updates[i];
				if ( updateSuffix == null) {
					continue;
				}
				String update = values.toStatement( updateSuffix );
				try {
					try (PreparedStatement ps = session
							.getJdbcCoordinator().getStatementPreparer()
							.prepareStatement( update, false )) {
						int position = 1; // jdbc params are 1-based
						for ( Object[] result : values.getIds() ) {
							for ( Object column : result ) {
								ps.setObject( position++, column );
							}
						}
						if ( assignmentParameterSpecifications[i] != null ) {
							for ( int x = 0; x < assignmentParameterSpecifications[i].length; x++ ) {
								position += assignmentParameterSpecifications[i][x]
										.bind( ps, queryParameters, session, position );
							}
						}
						session
								.getJdbcCoordinator().getResultSetReturn()
								.executeUpdate( ps );
					}
				}
				catch ( SQLException e ) {
					throw convert(
							e,
							"error performing bulk update",
							update
					);
				}
			}
		}

		return values.getIds().size();
	}
