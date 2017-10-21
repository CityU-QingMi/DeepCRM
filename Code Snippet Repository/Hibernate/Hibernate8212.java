	@Test
	public void testSequenceIdentifierGenerator() {
		Session s = null;
		Transaction tx = null;
		try {
			s = openSession();
			tx = s.beginTransaction();

			for ( int i = 0; i < 5; i++ ) {
				s.persist( new SequenceIdentifier() );
			}
			s.flush();

			assertEquals( 5, countInsertedRows( s ) );

			insertNewRow( s );
			insertNewRow( s );

			assertEquals( 7, countInsertedRows( s ) );

			List<Number> ids = s.createQuery( "SELECT id FROM sequenceIdentifier" ).list();
			for ( Number id : ids ) {
				log.debug( "Found id: " + id );
			}

			for ( int i = 0; i < 3; i++ ) {
				s.persist( new SequenceIdentifier() );
			}
			s.flush();

			assertEquals( 10, countInsertedRows( s ) );
		}
		finally {
			if ( tx != null ) {
				tx.rollback();
			}

			s.close();
		}
	}
