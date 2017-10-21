	private void checkException(Session mainSession, PersistenceException e) {
		final Throwable cause = e.getCause();
		if ( cause instanceof JDBCException ) {
			// SQLServer will report this condition via a SQLException
			// when using its SNAPSHOT transaction isolation...

			if ( !(getDialect() instanceof SQLServerDialect && ((JDBCException) cause).getErrorCode() == 3960) ) {
				throw e;
			}
			else {
				// it seems to "lose track" of the transaction as well...
				mainSession.getTransaction().rollback();
				mainSession.beginTransaction();
			}
		}
		else if ( !(cause instanceof StaleObjectStateException) && !(cause instanceof StaleStateException) ) {
			fail( "expectd StaleObjectStateException or StaleStateException exception but is" + cause );
		}
	}
