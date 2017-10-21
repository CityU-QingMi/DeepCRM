	@SuppressWarnings({""})
	public static void close(ResultSet resultSet) {
		log.tracef( "Closing result set [%s]", resultSet );

		try {
			resultSet.close();
		}
		catch (SQLException e) {
			log.debugf( "Unable to release JDBC result set [%s]", e.getMessage() );
		}
		catch (Exception e) {
			// try to handle general errors more elegantly
			log.debugf( "Unable to release JDBC result set [%s]", e.getMessage() );
		}
	}
