	public void testManyToOneAssignedIds() {
		// NOTES: Child defines a many-to-one back to its Parent.  This
		// association does not define persist cascading (which is natural;
		// a child should not be able to create its parent).
		try {
			Session s = openSession();
			s.beginTransaction();
			ParentAssigned p = new ParentAssigned( new Long( 1 ), "parent" );
			ChildAssigned c = new ChildAssigned( new Long( 2 ), "child" );
			c.setParent( p );
			s.persist( c );
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
