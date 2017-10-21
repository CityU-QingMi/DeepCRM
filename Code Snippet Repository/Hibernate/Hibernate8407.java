	public void testOneToOnePropertyRefAssignedIds() {
		try {
			Session s = openSession();
			s.beginTransaction();
			ChildAssigned c2 = new ChildAssigned( new Long( 3 ), "c3" );
			ChildInfoAssigned info = new ChildInfoAssigned( new Long( 4 ), "blah blah blah" );
			c2.setInfo( info );
			info.setOwner( c2 );
			s.persist( c2 );
			try {
				s.getTransaction().commit();
				fail( "expecting TransientObjectException on flush" );
			}
			catch( TransientObjectException e ) {
				// expected result
				log.trace( "handled expected exception : " + e );
				s.getTransaction().rollback();
			}
			finally {
				s.close();
			}
		}
		finally {
			cleanupData();
		}
	}
