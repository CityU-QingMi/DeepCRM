	public void testOneToOneAssignedIds() {
		try {
			Session s = openSession();
			s.beginTransaction();
			ParentAssigned p = new ParentAssigned( new Long( 1 ), "parent" );
			ParentInfoAssigned info = new ParentInfoAssigned( "something secret" );
			p.setInfo( info );
			info.setOwner( p );
			s.persist( p );
			try {
				s.getTransaction().commit();
				fail( "expecting TransientObjectException on flush" );
			}
			catch( TransientObjectException e ) {
				// expected result
				log.trace( "handled expected exception", e );
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
