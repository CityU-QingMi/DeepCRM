	@Override
	public Connection getIsolatedConnection() {
		try {
			return jdbcContext.getJdbcConnectionAccess().obtainConnection();
		}
		catch (SQLException e) {
			// should never happen
			throw new SchemaManagementException( "Error accessing user-provided Connection via JdbcConnectionAccessProvidedConnectionImpl", e );
		}
	}
