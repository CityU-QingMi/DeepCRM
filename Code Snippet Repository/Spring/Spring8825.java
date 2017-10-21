	private void closeResultSet(@Nullable ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			}
			catch (SQLException ex) {
				logger.trace("Could not close CCI ResultSet", ex);
			}
			catch (Throwable ex) {
				// We don't trust the CCI driver: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing CCI ResultSet", ex);
			}
		}
	}
