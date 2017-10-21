	@Override
	public int execute(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters) {

		CteValuesListBuilder values = prepareCteStatement( session, queryParameters );

		if ( !values.getIds().isEmpty() ) {
			// Start performing the deletes
			for ( String deleteSuffix : deletes ) {
				if ( deleteSuffix == null) {
					continue;
				}

				String delete = values.toStatement( deleteSuffix );

				try {
					try ( PreparedStatement ps = session
							.getJdbcCoordinator().getStatementPreparer()
							.prepareStatement( delete, false ) ) {
						int pos = 1;
						for ( Object[] result : values.getIds() ) {
							for ( Object column : result ) {
								ps.setObject( pos++, column );
							}
						}
						session
								.getJdbcCoordinator().getResultSetReturn()
								.executeUpdate( ps );
					}
				}
				catch ( SQLException e ) {
					throw convert( e, "error performing bulk delete", delete );
				}
			}
		}

		return values.getIds().size();
	}
