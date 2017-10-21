	public void testOneToOneGeneratedIds() {
		try {
			Session s = openSession();
			s.beginTransaction();
			Parent p = new Parent( "parent" );
			ParentInfo info = new ParentInfo( "xyz" );
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
