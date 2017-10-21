	@Test
	@Priority(10)
	public void initData() {
		final Session s = openSession();
		try {
			// revision 1 - persist the project entity
			s.getTransaction().begin();
			final Project project = new Project( 1, "fooName" );
			s.persist( project );
			s.getTransaction().commit();

			// detach the project entity
			s.clear();

			// revision 2 to 6 - update the detached project entity.
			for( int i = 0; i < 5; ++i ) {
				s.getTransaction().begin();
				project.setName( "fooName" + ( i + 2 ) );
				s.update( project );
				s.getTransaction().commit();
				s.clear();
			}
		}
		catch ( Throwable t ) {
			if ( s.getTransaction().isActive() ) {
				s.getTransaction().rollback();
			}
			throw t;
		}
		finally {
			s.close();
		}
	}
