	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();

		// Determine default auto-commit and transaction isolation
		// via a Connection from the target DataSource, if possible.
		if (this.defaultAutoCommit == null || this.defaultTransactionIsolation == null) {
			try {
				Connection con = obtainTargetDataSource().getConnection();
				try {
					checkDefaultConnectionProperties(con);
				}
				finally {
					con.close();
				}
			}
			catch (SQLException ex) {
				logger.warn("Could not retrieve default auto-commit and transaction isolation settings", ex);
			}
		}
	}
