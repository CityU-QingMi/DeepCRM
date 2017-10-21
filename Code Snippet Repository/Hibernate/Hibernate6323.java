	private void assertDatabaseConstraintViolationThrown(Object o) {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.persist( o );
			s.flush();
			fail( "expecting SQL constraint violation" );
		}
		catch (PersistenceException pe) {
			final Throwable cause = pe.getCause();
			if ( cause instanceof ConstraintViolationException ) {
				fail( "invalid object should not be validated" );
			}
			else if ( cause instanceof org.hibernate.exception.ConstraintViolationException ) {
				if ( getDialect().supportsColumnCheck() ) {
					// expected
				}
				else {
					org.hibernate.exception.ConstraintViolationException cve = (org.hibernate.exception.ConstraintViolationException) cause;
					fail( "Unexpected SQL constraint violation [" + cve.getConstraintName() + "] : " + cve.getSQLException() );
				}
			}
		}
		tx.rollback();
		s.close();
	}
