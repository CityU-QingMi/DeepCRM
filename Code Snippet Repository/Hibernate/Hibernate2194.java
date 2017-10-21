	private StatementPreparationTemplate buildPreparedStatementPreparationTemplate(String sql, final boolean isCallable) {
		return new StatementPreparationTemplate( sql ) {
			@Override
			protected PreparedStatement doPrepare() throws SQLException {
				return isCallable
						? connection().prepareCall( sql )
						: connection().prepareStatement( sql );
			}
		};
	}
