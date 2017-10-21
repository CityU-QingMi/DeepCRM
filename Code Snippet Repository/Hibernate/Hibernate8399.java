	private void cleanupData() {
		Session s = null;
		try {
			s = openSession();
			s.beginTransaction();
			s.createQuery( "delete ChildInfoAssigned" ).executeUpdate();
			s.createQuery( "delete ChildAssigned" ).executeUpdate();
			s.createQuery( "delete ParentAssigned" ).executeUpdate();
			s.createQuery( "delete ChildInfoAssigned" ).executeUpdate();
			s.createQuery( "delete ChildAssigned" ).executeUpdate();
			s.createQuery( "delete ParentAssigned" ).executeUpdate();
			s.getTransaction().commit();
		}
		catch( Throwable t ) {
			log.warn( "unable to cleanup test data : " + t );
		}
		finally {
			if ( s != null ) {
				try {
					s.close();
				}
				catch( Throwable ignore ) {
				}
			}
		}
	}
