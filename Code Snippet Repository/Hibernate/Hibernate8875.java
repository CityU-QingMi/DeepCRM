	@Test
	public void testReattachmentUnmodifiedNaturalIdCheck() throws Throwable {
		Session s = openSession();
		s.beginTransaction();
		User u = new User( "gavin", "hb", "secret" );
		s.persist( u );
		s.getTransaction().commit();
		s.close();

		
		s = openSession();
		s.beginTransaction();
		try {
			s.buildLockRequest(LockOptions.NONE).lock(u);
			Field name = u.getClass().getDeclaredField("name");
			name.setAccessible(true);
			name.set(u, "Gavin");
			assertNotNull(s.byNaturalId(User.class).using("name","Gavin").using("org", "hb").load());
			s.getTransaction().commit();
		}
		catch( HibernateException expected ) {
			s.getTransaction().rollback();
		}
		catch( Throwable t ) {
			try {
				s.getTransaction().rollback();
			}
			catch ( Throwable ignore ) {
			}
			throw t;
		}
		finally {
			s.close();
		}

		s = openSession();
		s.beginTransaction();
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}
